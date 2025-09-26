package co.edu.usbcali.sebastech.mapper;

import co.edu.usbcali.sebastech.domain.Pedido;
import co.edu.usbcali.sebastech.domain.Usuario;
import co.edu.usbcali.sebastech.dto.PedidoRequestDTO;
import co.edu.usbcali.sebastech.dto.PedidoResponseDTO;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class PedidoMapper {
    public static PedidoResponseDTO entityToDto(Pedido p) {
        Integer usuarioId = null; String usuarioNombre = null;
        if (p.getUsuario() != null) { usuarioId = p.getUsuario().getIdUsuario(); usuarioNombre = p.getUsuario().getNombre(); }
        return PedidoResponseDTO.builder()
                .id(p.getIdPedidos())
                .usuarioId(usuarioId)
                .usuarioNombre(usuarioNombre)
                .fecha(p.getFecha())
                .estado(p.getEstado())
                .total(p.getTotal())
                .build();
    }

    public static List<PedidoResponseDTO> entityToDtoList(List<Pedido> pedidos) {
        return pedidos.stream().map(PedidoMapper::entityToDto).toList();
    }

    public static Pedido requestDtoToEntity(PedidoRequestDTO dto, Usuario usuario) {
        return Pedido.builder()
                .usuario(usuario)
                .fecha(Timestamp.from(Instant.now()))
                .estado(dto.getEstado() == null ? "pendiente" : dto.getEstado())
                .total(dto.getTotal())
                .build();
    }
}
