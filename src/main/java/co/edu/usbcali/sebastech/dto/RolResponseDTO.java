package co.edu.usbcali.sebastech.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RolResponseDTO {
    private Integer idRol;
    private String nombre;
    private String descripcion;
}