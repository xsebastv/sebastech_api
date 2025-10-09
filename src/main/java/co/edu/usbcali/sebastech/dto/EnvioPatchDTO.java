package co.edu.usbcali.sebastech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvioPatchDTO {
    private Integer pedidoId;
    private String direccionEnvio;
    private String empresaEnvio;
    private String numeroGuia;
    private String estadoEnvio;
}