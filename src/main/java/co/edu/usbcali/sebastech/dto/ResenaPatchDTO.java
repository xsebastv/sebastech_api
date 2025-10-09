package co.edu.usbcali.sebastech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResenaPatchDTO {
    private Integer usuarioId;
    private Integer productoId;
    private Integer calificacion;
    private String comentario;
}