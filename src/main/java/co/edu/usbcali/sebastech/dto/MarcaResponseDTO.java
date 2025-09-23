package co.edu.usbcali.sebastech.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MarcaResponseDTO {
    private Integer idMarca;
    private String nombre;
    private String paisOrigen;
}
