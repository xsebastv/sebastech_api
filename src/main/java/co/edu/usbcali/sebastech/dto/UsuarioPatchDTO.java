package co.edu.usbcali.sebastech.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPatchDTO {
    private String firstName;
    private String lastName;
    @Email(message = "email no es válido")
    private String email;
    private String phone;
    private String password;
    private Integer roleId;
}
