package co.edu.usbcali.sebastech.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoriaResponseDTO {
    private Integer idCategoria;
    private String nombre;
    private String descripcion;
}
