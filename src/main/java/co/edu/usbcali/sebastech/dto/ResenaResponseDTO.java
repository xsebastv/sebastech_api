package co.edu.usbcali.sebastech.dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Builder
@Getter
public class ResenaResponseDTO {
    private Integer id;
    private Integer usuarioId;
    private String usuarioNombre;
    private Integer productoId;
    private String productoNombre;
    private Integer calificacion;
    private String comentario;
    private Timestamp fecha;
}
