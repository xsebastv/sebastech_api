package co.edu.usbcali.sebastech.mapper;

import co.edu.usbcali.sebastech.domain.MetodoPago;
import co.edu.usbcali.sebastech.domain.Usuario;
import co.edu.usbcali.sebastech.dto.MetodoPagoRequestDTO;
import co.edu.usbcali.sebastech.dto.MetodoPagoResponseDTO;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class MetodoPagoMapper {
    public static MetodoPagoResponseDTO entityToDto(MetodoPago m) {
        Integer usuarioId = null; String usuarioNombre = null;
        if (m.getUsuario() != null) { usuarioId = m.getUsuario().getIdUsuario(); usuarioNombre = m.getUsuario().getNombre(); }
        return MetodoPagoResponseDTO.builder()
                .id(m.getIdMetodo())
                .usuarioId(usuarioId)
                .usuarioNombre(usuarioNombre)
                .tipo(m.getTipo())
                .proveedor(m.getProveedor())
                .numeroEnmascarado(m.getNumeroEnmascarado())
                .fechaRegistro(m.getFechaRegistro())
                .build();
    }

    public static List<MetodoPagoResponseDTO> entityToDtoList(List<MetodoPago> metodos) {
        return metodos.stream().map(MetodoPagoMapper::entityToDto).toList();
    }

    public static MetodoPago requestDtoToEntity(MetodoPagoRequestDTO dto, Usuario usuario) {
        return MetodoPago.builder()
                .usuario(usuario)
                .tipo(dto.getTipo())
                .proveedor(dto.getProveedor())
                .numeroEnmascarado(dto.getNumeroEnmascarado())
                .fechaRegistro(Timestamp.from(Instant.now()))
                .build();
    }
}
