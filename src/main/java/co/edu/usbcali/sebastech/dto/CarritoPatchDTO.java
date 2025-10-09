package co.edu.usbcali.sebastech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoPatchDTO {
    private Integer usuarioId;
    private Integer productoId;
    private Integer cantidad;
}