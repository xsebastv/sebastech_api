package co.edu.usbcali.sebastech.dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Builder
@Getter
public class MetodoPagoResponseDTO {
    private Integer id;
    private Integer usuarioId;
    private String usuarioNombre;
    private String tipo;
    private String proveedor;
    private String numeroEnmascarado;
    private Timestamp fechaRegistro;
}
