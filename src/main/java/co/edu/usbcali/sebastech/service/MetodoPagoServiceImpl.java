package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.domain.MetodoPago;
import co.edu.usbcali.sebastech.domain.Usuario;
import co.edu.usbcali.sebastech.dto.MetodoPagoRequestDTO;
import co.edu.usbcali.sebastech.dto.MetodoPagoResponseDTO;
import co.edu.usbcali.sebastech.dto.MetodoPagoPatchDTO;
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
    public List<String> obtenerMetodosPago() {
        return metodoPagoRepository.findAll().stream()
                .map(MetodoPago::getTipo)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MetodoPagoResponseDTO> obtenerMetodos() {
        return MetodoPagoMapper.entityToDtoList(metodoPagoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public MetodoPagoResponseDTO obtenerMetodoPorId(Integer id) throws Exception {
        MetodoPago metodoPago = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Método de pago no encontrado"));
        return MetodoPagoMapper.entityToDto(metodoPago);
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
    public MetodoPagoResponseDTO updateMetodo(Integer id, MetodoPagoRequestDTO request) throws Exception {
        MetodoPago metodoPago = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Método de pago no encontrado"));
        
        if (request == null) throw new BadRequestException("No se puede asociar el método de pago");
        if (request.getUsuarioId() == null) throw new BadRequestException("usuarioId es obligatorio");
        
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        
        // Actualizar todos los campos (PUT completo)
        metodoPago.setUsuario(usuario);
        metodoPago.setTipo(request.getTipo());
        metodoPago.setProveedor(request.getProveedor());
        metodoPago.setNumeroEnmascarado(request.getNumeroEnmascarado());
        
        metodoPago = metodoPagoRepository.save(metodoPago);
        return MetodoPagoMapper.entityToDto(metodoPago);
    }

    @Override
    @Transactional
    public MetodoPagoResponseDTO patchMetodoPago(Integer id, MetodoPagoPatchDTO patchDTO) throws Exception {
        MetodoPago metodoPago = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Método de pago no encontrado"));
        
        if (patchDTO == null) throw new BadRequestException("Datos inválidos");
        
        // Solo actualizar campos que no sean null
        if (patchDTO.getTipo() != null && !patchDTO.getTipo().isBlank()) {
            metodoPago.setTipo(patchDTO.getTipo());
        }
        if (patchDTO.getProveedor() != null) {
            metodoPago.setProveedor(patchDTO.getProveedor());
        }
        if (patchDTO.getNumeroEnmascarado() != null) {
            metodoPago.setNumeroEnmascarado(patchDTO.getNumeroEnmascarado());
        }
        if (patchDTO.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(patchDTO.getUsuarioId())
                    .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
            metodoPago.setUsuario(usuario);
        }
        
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
