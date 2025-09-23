package co.edu.usbcali.sebastech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {
    @NotBlank(message = "firstName es obligatorio")
    private String firstName;

    @NotBlank(message = "lastName es obligatorio")
    private String lastName;

    @NotBlank(message = "email es obligatorio")
    @Email(message = "email no es válido")
    private String email;

    @NotBlank(message = "phone es obligatorio")
    private String phone;

    @NotBlank(message = "password es obligatorio")
    private String password;

    @NotNull(message = "roleId es obligatorio")
    private Integer roleId;
}
