package co.edu.usbcali.sebastech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetodoPagoRequestDTO {
    private Integer usuarioId;
    private String tipo;
    private String proveedor;
    private String numeroEnmascarado;
}
