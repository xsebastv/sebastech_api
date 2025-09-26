package co.edu.usbcali.sebastech.mapper;

import co.edu.usbcali.sebastech.domain.Producto;
import co.edu.usbcali.sebastech.domain.Resena;
import co.edu.usbcali.sebastech.domain.Usuario;
import co.edu.usbcali.sebastech.dto.ResenaRequestDTO;
import co.edu.usbcali.sebastech.dto.ResenaResponseDTO;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class ResenaMapper {
    public static ResenaResponseDTO entityToDto(Resena r) {
        Integer usuarioId = null; String usuarioNombre = null; Integer productoId = null; String productoNombre = null;
        if (r.getUsuario() != null) { usuarioId = r.getUsuario().getIdUsuario(); usuarioNombre = r.getUsuario().getNombre(); }
        if (r.getProducto() != null) { productoId = r.getProducto().getIdProductos(); productoNombre = r.getProducto().getNombre(); }
        return ResenaResponseDTO.builder()
                .id(r.getIdResena())
                .usuarioId(usuarioId)
                .usuarioNombre(usuarioNombre)
                .productoId(productoId)
                .productoNombre(productoNombre)
                .calificacion(r.getCalificacion())
                .comentario(r.getComentario())
                .fecha(r.getFecha())
                .build();
    }

    public static List<ResenaResponseDTO> entityToDtoList(List<Resena> resenas) {
        return resenas.stream().map(ResenaMapper::entityToDto).toList();
    }

    public static Resena requestDtoToEntity(ResenaRequestDTO dto, Usuario usuario, Producto producto) {
        return Resena.builder()
                .usuario(usuario)
                .producto(producto)
                .calificacion(dto.getCalificacion())
                .comentario(dto.getComentario())
                .fecha(Timestamp.from(Instant.now()))
                .build();
    }
}
