package co.edu.usbcali.sebastech.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UsuarioResponseDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Boolean status;
    private String roleName;
    private Integer roleId;
}
