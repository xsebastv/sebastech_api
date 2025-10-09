package co.edu.usbcali.sebastech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetodoPagoPatchDTO {
    private Integer usuarioId;
    private String tipo;
    private String proveedor;
    private String numeroEnmascarado;
}