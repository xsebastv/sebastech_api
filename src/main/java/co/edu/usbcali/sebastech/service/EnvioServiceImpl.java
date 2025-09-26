package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.domain.Envio;
import co.edu.usbcali.sebastech.domain.Pedido;
import co.edu.usbcali.sebastech.dto.EnvioRequestDTO;
import co.edu.usbcali.sebastech.dto.EnvioResponseDTO;
import co.edu.usbcali.sebastech.exception.BadRequestException;
import co.edu.usbcali.sebastech.exception.NotFoundException;
import co.edu.usbcali.sebastech.mapper.EnvioMapper;
import co.edu.usbcali.sebastech.repository.EnvioRepository;
import co.edu.usbcali.sebastech.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnvioServiceImpl implements EnvioService {

    private final EnvioRepository envioRepository;
    private final PedidoRepository pedidoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EnvioResponseDTO> obtenerEnvios() {
        return EnvioMapper.entityToDtoList(envioRepository.findAll());
    }

    @Override
    @Transactional
    public EnvioResponseDTO saveEnvio(EnvioRequestDTO request) throws Exception {
        if (request == null) throw new BadRequestException("No se puede asociar el envío");
        if (request.getPedidoId() == null) throw new BadRequestException("pedidoId es obligatorio");
        Pedido pedido = pedidoRepository.findById(request.getPedidoId()).orElseThrow(() -> new NotFoundException("Pedido no encontrado"));
        Envio envio = EnvioMapper.requestDtoToEntity(request, pedido);
        envio = envioRepository.save(envio);
        return EnvioMapper.entityToDto(envio);
    }

    @Override
    @Transactional
    public void deleteEnvio(Integer id) throws Exception {
        if (!envioRepository.existsById(id)) throw new NotFoundException("Envío no encontrado");
        envioRepository.deleteById(id);
    }
}
