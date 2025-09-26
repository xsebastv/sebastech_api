package co.edu.usbcali.sebastech.mapper;

import co.edu.usbcali.sebastech.domain.Carrito;
import co.edu.usbcali.sebastech.domain.Producto;
import co.edu.usbcali.sebastech.domain.Usuario;
import co.edu.usbcali.sebastech.dto.CarritoRequestDTO;
import co.edu.usbcali.sebastech.dto.CarritoResponseDTO;

import java.util.List;

public class CarritoMapper {
    public static CarritoResponseDTO entityToDto(Carrito c) {
        Integer usuarioId = null; String usuarioNombre = null; Integer productoId = null; String productoNombre = null;
        if (c.getUsuario() != null) { usuarioId = c.getUsuario().getIdUsuario(); usuarioNombre = c.getUsuario().getNombre(); }
        if (c.getProducto() != null) { productoId = c.getProducto().getIdProductos(); productoNombre = c.getProducto().getNombre(); }
        return CarritoResponseDTO.builder()
                .id(c.getIdCarrito())
                .usuarioId(usuarioId)
                .usuarioNombre(usuarioNombre)
                .productoId(productoId)
                .productoNombre(productoNombre)
                .cantidad(c.getCantidad())
                .build();
    }

    public static List<CarritoResponseDTO> entityToDtoList(List<Carrito> items) {
        return items.stream().map(CarritoMapper::entityToDto).toList();
    }

    public static Carrito requestDtoToEntity(CarritoRequestDTO dto, Usuario usuario, Producto producto) {
        return Carrito.builder()
                .usuario(usuario)
                .producto(producto)
                .cantidad(dto.getCantidad())
                .build();
    }
}
