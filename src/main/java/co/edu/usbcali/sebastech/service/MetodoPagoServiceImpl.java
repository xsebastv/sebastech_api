package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.domain.MetodoPago;
import co.edu.usbcali.sebastech.domain.Usuario;
import co.edu.usbcali.sebastech.dto.MetodoPagoRequestDTO;
import co.edu.usbcali.sebastech.dto.MetodoPagoResponseDTO;
import co.edu.usbcali.sebastech.exception.BadRequestException;
import co.edu.usbcali.sebastech.exception.NotFoundException;
import co.edu.usbcali.sebastech.mapper.MetodoPagoMapper;
import co.edu.usbcali.sebastech.repository.MetodoPagoRepository;
import co.edu.usbcali.sebastech.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MetodoPagoServiceImpl implements MetodoPagoService {

    private final MetodoPagoRepository metodoPagoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MetodoPagoResponseDTO> obtenerMetodos() {
        return MetodoPagoMapper.entityToDtoList(metodoPagoRepository.findAll());
    }

    @Override
    @Transactional
    public MetodoPagoResponseDTO saveMetodo(MetodoPagoRequestDTO request) throws Exception {
        if (request == null) throw new BadRequestException("No se puede asociar el método de pago");
        if (request.getUsuarioId() == null) throw new BadRequestException("usuarioId es obligatorio");
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId()).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        MetodoPago metodoPago = MetodoPagoMapper.requestDtoToEntity(request, usuario);
        metodoPago = metodoPagoRepository.save(metodoPago);
        return MetodoPagoMapper.entityToDto(metodoPago);
    }

    @Override
    @Transactional
    public void deleteMetodo(Integer id) throws Exception {
        if (!metodoPagoRepository.existsById(id)) throw new NotFoundException("Método de pago no encontrado");
        metodoPagoRepository.deleteById(id);
    }
}
