package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.domain.DetallePedido;
import co.edu.usbcali.sebastech.domain.Pedido;
import co.edu.usbcali.sebastech.domain.Producto;
import co.edu.usbcali.sebastech.dto.DetallePedidoRequestDTO;
import co.edu.usbcali.sebastech.dto.DetallePedidoResponseDTO;
import co.edu.usbcali.sebastech.dto.DetallePedidoPatchDTO;
import co.edu.usbcali.sebastech.exception.BadRequestException;
import co.edu.usbcali.sebastech.exception.NotFoundException;
import co.edu.usbcali.sebastech.mapper.DetallePedidoMapper;
import co.edu.usbcali.sebastech.repository.DetallePedidoRepository;
import co.edu.usbcali.sebastech.repository.PedidoRepository;
import co.edu.usbcali.sebastech.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DetallePedidoResponseDTO> obtenerDetalles() {
        return DetallePedidoMapper.entityToDtoList(detallePedidoRepository.findAll());
    }

    @Override
    @Transactional
    public DetallePedidoResponseDTO saveDetalle(DetallePedidoRequestDTO request) throws Exception {
        if (request == null) throw new BadRequestException("No se puede asociar el detalle");
        if (request.getPedidoId() == null || request.getProductoId() == null) throw new BadRequestException("pedidoId y productoId son obligatorios");
        Pedido pedido = pedidoRepository.findById(request.getPedidoId()).orElseThrow(() -> new NotFoundException("Pedido no encontrado"));
        Producto producto = productoRepository.findById(request.getProductoId()).orElseThrow(() -> new NotFoundException("Producto no encontrado"));
        DetallePedido detalle = DetallePedidoMapper.requestDtoToEntity(request, pedido, producto);
        detalle = detallePedidoRepository.save(detalle);
        return DetallePedidoMapper.entityToDto(detalle);
    }

    @Override
    @Transactional
    public DetallePedidoResponseDTO patchDetallePedido(Integer id, DetallePedidoPatchDTO patchDTO) throws Exception {
        DetallePedido detalle = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Detalle de pedido no encontrado"));
        
        if (patchDTO == null) throw new BadRequestException("Datos inválidos");
        
        // Solo actualizar campos que no sean null
        if (patchDTO.getCantidad() != null) {
            detalle.setCantidad(patchDTO.getCantidad());
        }
        if (patchDTO.getPrecioUnitario() != null) {
            detalle.setPrecioUnitario(patchDTO.getPrecioUnitario());
        }
        if (patchDTO.getPedidoId() != null) {
            Pedido pedido = pedidoRepository.findById(patchDTO.getPedidoId())
                    .orElseThrow(() -> new NotFoundException("Pedido no encontrado"));
            detalle.setPedido(pedido);
        }
        if (patchDTO.getProductoId() != null) {
            Producto producto = productoRepository.findById(patchDTO.getProductoId())
                    .orElseThrow(() -> new NotFoundException("Producto no encontrado"));
            detalle.setProducto(producto);
        }
        
        detalle = detallePedidoRepository.save(detalle);
        return DetallePedidoMapper.entityToDto(detalle);
    }

    @Override
    @Transactional
    public void deleteDetalle(Integer id) throws Exception {
        if (!detallePedidoRepository.existsById(id)) throw new NotFoundException("Detalle no encontrado");
        detallePedidoRepository.deleteById(id);
    }
}
