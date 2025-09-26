package co.edu.usbcali.sebastech.dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Builder
@Getter
public class EnvioResponseDTO {
    private Integer id;
    private Integer pedidoId;
    private String direccionEnvio;
    private String empresaEnvio;
    private String numeroGuia;
    private Timestamp fechaEnvio;
    private Timestamp fechaEntregaEstimada;
    private String estadoEnvio;
}
