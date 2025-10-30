package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.domain.Pedido;
import co.edu.usbcali.sebastech.domain.Usuario;
import co.edu.usbcali.sebastech.dto.PedidoRequestDTO;
import co.edu.usbcali.sebastech.dto.PedidoResponseDTO;
import co.edu.usbcali.sebastech.dto.PedidoPatchDTO;
import co.edu.usbcali.sebastech.exception.BadRequestException;
import co.edu.usbcali.sebastech.exception.NotFoundException;
import co.edu.usbcali.sebastech.mapper.PedidoMapper;
import co.edu.usbcali.sebastech.repository.PedidoRepository;
import co.edu.usbcali.sebastech.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> obtenerPedidos() {
        return PedidoMapper.entityToDtoList(pedidoRepository.findAll());
    }

    @Override
    @Transactional
    public PedidoResponseDTO savePedido(PedidoRequestDTO request) throws Exception {
        if (request == null) throw new BadRequestException("No se puede asociar el pedido");
        if (request.getUsuarioId() == null) throw new BadRequestException("usuarioId es obligatorio");
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        Pedido pedido = PedidoMapper.requestDtoToEntity(request, usuario);
        pedido = pedidoRepository.save(pedido);
        return PedidoMapper.entityToDto(pedido);
    }

    @Override
    @Transactional(readOnly = true)
    public PedidoResponseDTO findById(Integer id) throws Exception {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new NotFoundException("Pedido no encontrado"));
        return PedidoMapper.entityToDto(pedido);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PedidoResponseDTO patchPedido(Integer id, PedidoPatchDTO patchDTO) throws Exception {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pedido no encontrado"));
        
        if (patchDTO == null) throw new BadRequestException("Datos inválidos");
        
        // Solo actualizar campos que no sean null
        if (patchDTO.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(patchDTO.getUsuarioId())
                    .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
            pedido.setUsuario(usuario);
        }
        if (patchDTO.getEstado() != null && !patchDTO.getEstado().isBlank()) {
            pedido.setEstado(patchDTO.getEstado());
        }
        if (patchDTO.getTotal() != null) {
            pedido.setTotal(patchDTO.getTotal());
        }
        
        pedido = pedidoRepository.save(pedido);
        return PedidoMapper.entityToDto(pedido);
    }

    @Override
    @Transactional
    public void deletePedido(Integer id) throws Exception {
        if (!pedidoRepository.existsById(id)) throw new NotFoundException("Pedido no encontrado");
        pedidoRepository.deleteById(id);
    }
}
