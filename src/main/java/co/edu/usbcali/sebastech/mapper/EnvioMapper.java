package co.edu.usbcali.sebastech.mapper;

import co.edu.usbcali.sebastech.domain.Envio;
import co.edu.usbcali.sebastech.domain.Pedido;
import co.edu.usbcali.sebastech.dto.EnvioRequestDTO;
import co.edu.usbcali.sebastech.dto.EnvioResponseDTO;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class EnvioMapper {
    public static EnvioResponseDTO entityToDto(Envio e) {
        Integer pedidoId = null;
        if (e.getPedido() != null) pedidoId = e.getPedido().getIdPedidos();
        return EnvioResponseDTO.builder()
                .id(e.getIdEnvio())
                .pedidoId(pedidoId)
                .direccionEnvio(e.getDireccionEnvio())
                .empresaEnvio(e.getEmpresaEnvio())
                .numeroGuia(e.getNumeroGuia())
                .fechaEnvio(e.getFechaEnvio())
                .fechaEntregaEstimada(e.getFechaEntregaEstimada())
                .estadoEnvio(e.getEstadoEnvio())
                .build();
    }

    public static List<EnvioResponseDTO> entityToDtoList(List<Envio> envios) {
        return envios.stream().map(EnvioMapper::entityToDto).toList();
    }

    public static Envio requestDtoToEntity(EnvioRequestDTO dto, Pedido pedido) {
        return Envio.builder()
                .pedido(pedido)
                .direccionEnvio(dto.getDireccionEnvio())
                .empresaEnvio(dto.getEmpresaEnvio())
                .numeroGuia(dto.getNumeroGuia())
                .fechaEnvio(Timestamp.from(Instant.now()))
                .estadoEnvio(dto.getEstadoEnvio() == null ? "en preparación" : dto.getEstadoEnvio())
                .build();
    }
}
