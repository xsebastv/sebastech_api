package co.edu.usbcali.sebastech.mapper;

import co.edu.usbcali.sebastech.domain.DetallePedido;
import co.edu.usbcali.sebastech.domain.Pedido;
import co.edu.usbcali.sebastech.domain.Producto;
import co.edu.usbcali.sebastech.dto.DetallePedidoRequestDTO;
import co.edu.usbcali.sebastech.dto.DetallePedidoResponseDTO;

import java.util.List;

public class DetallePedidoMapper {
    public static DetallePedidoResponseDTO entityToDto(DetallePedido d) {
        Integer pedidoId = null; Integer productoId = null; String productoNombre = null;
        if (d.getPedido() != null) pedidoId = d.getPedido().getIdPedidos();
        if (d.getProducto() != null) { productoId = d.getProducto().getIdProductos(); productoNombre = d.getProducto().getNombre(); }
        return DetallePedidoResponseDTO.builder()
                .id(d.getIdDetallesPedidos())
                .pedidoId(pedidoId)
                .productoId(productoId)
                .productoNombre(productoNombre)
                .cantidad(d.getCantidad())
                .precioUnitario(d.getPrecioUnitario())
                .build();
    }

    public static List<DetallePedidoResponseDTO> entityToDtoList(List<DetallePedido> detalles) {
        return detalles.stream().map(DetallePedidoMapper::entityToDto).toList();
    }

    public static DetallePedido requestDtoToEntity(DetallePedidoRequestDTO dto, Pedido pedido, Producto producto) {
        return DetallePedido.builder()
                .pedido(pedido)
                .producto(producto)
                .cantidad(dto.getCantidad())
                .precioUnitario(dto.getPrecioUnitario())
                .build();
    }
}
