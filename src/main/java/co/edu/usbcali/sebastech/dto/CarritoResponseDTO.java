package co.edu.usbcali.sebastech.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CarritoResponseDTO {
    private Integer id;
    private Integer usuarioId;
    private String usuarioNombre;
    private Integer productoId;
    private String productoNombre;
    private Integer cantidad;
}
