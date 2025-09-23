package co.edu.usbcali.sebastech.mapper;

import co.edu.usbcali.sebastech.domain.Rol;
import co.edu.usbcali.sebastech.domain.Usuario;
import co.edu.usbcali.sebastech.dto.UsuarioRequestDTO;
import co.edu.usbcali.sebastech.dto.UsuarioResponseDTO;

import java.util.List;

public class UsuarioMapper {

    public static UsuarioResponseDTO entityToDto(Usuario usuario) {
        String rolNombre = null;
        Integer roleId = null;
        if (usuario.getRol() != null) {
            rolNombre = usuario.getRol().getNombre();
            roleId = usuario.getRol().getIdRol();
        }
        // Derivar firstName y lastName desde 'nombre' del dominio (si viene unido)
        String firstName = null;
        String lastName = null;
        if (usuario.getNombre() != null) {
            String[] parts = usuario.getNombre().trim().split(" ", 2);
            firstName = parts[0];
            lastName = parts.length > 1 ? parts[1] : null;
        }
        return UsuarioResponseDTO.builder()
                .id(usuario.getIdUsuario())
                .firstName(firstName)
                .lastName(lastName)
                .email(usuario.getEmail())
                .phone(usuario.getTelefono())
                .status(Boolean.TRUE)
                .roleName(rolNombre)
                .roleId(roleId)
                .build();
    }

    public static List<UsuarioResponseDTO> entityToDtoList(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioMapper::entityToDto).toList();
    }

    public static Usuario requestDtoToEntity(UsuarioRequestDTO dto, Rol rol) {
        String nombre = null;
        if (dto.getFirstName() != null || dto.getLastName() != null) {
            String fn = dto.getFirstName() == null ? "" : dto.getFirstName().trim();
            String ln = dto.getLastName() == null ? "" : dto.getLastName().trim();
            nombre = (fn + " " + ln).trim();
        }
        return Usuario.builder()
                .nombre(nombre)
                .email(dto.getEmail())
                .contrasena(dto.getPassword())
                .telefono(dto.getPhone())
                .rol(rol)
                .build();
    }

    // Aliases para mantener compatibilidad con estilos de nombres alternativos
    public static UsuarioResponseDTO entityToResponseDTO(Usuario usuario) {
        return entityToDto(usuario);
    }

    public static List<UsuarioResponseDTO> entityToResponseDTOList(List<Usuario> usuarios) {
        return entityToDtoList(usuarios);
    }

    public static Usuario requestDTOToEntity(UsuarioRequestDTO dto, Rol rol) {
        return requestDtoToEntity(dto, rol);
    }
}
