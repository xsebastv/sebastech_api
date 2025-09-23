package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.domain.Rol;
import co.edu.usbcali.sebastech.domain.Usuario;
import co.edu.usbcali.sebastech.dto.UsuarioRequestDTO;
import co.edu.usbcali.sebastech.dto.UsuarioResponseDTO;
import co.edu.usbcali.sebastech.exception.BadRequestException;
import co.edu.usbcali.sebastech.exception.ConflictException;
import co.edu.usbcali.sebastech.exception.NotFoundException;
import co.edu.usbcali.sebastech.mapper.UsuarioMapper;
import co.edu.usbcali.sebastech.repository.RolRepository;
import co.edu.usbcali.sebastech.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Override
    @Transactional(readOnly = true)
    public List<String> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<String> nombres = new ArrayList<>();
        for (Usuario u : usuarios) {
            nombres.add(u.getNombre());
        }
        return nombres;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> obtenerUsuariosResponseDTO() {
        return UsuarioMapper.entityToResponseDTOList(
                usuarioRepository.findAll()
        );
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public UsuarioResponseDTO saveUsuario(UsuarioRequestDTO request) throws Exception {
        if (request == null) throw new BadRequestException("No se puede asociar el usuario");
        if ((request.getFirstName() == null || request.getFirstName().isBlank()) && (request.getLastName() == null || request.getLastName().isBlank()))
            throw new BadRequestException("El nombre es obligatorio");
        if (request.getEmail() == null || request.getEmail().isBlank()) throw new BadRequestException("El email es obligatorio");
        if (request.getPassword() == null || request.getPassword().isBlank()) throw new BadRequestException("La contraseña es obligatoria");

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException("Ya existe un usuario con ese email");
        }

        // Validación de dependencia con Rol
        // Validar si existe el rol por el id
    Rol rol = rolRepository.findById(request.getRoleId())
        .orElseThrow(() -> new NotFoundException("Rol no encontrado"));

        // Convertir a Entity desde el RequestDTO
        Usuario usuario = UsuarioMapper.requestDtoToEntity(request, rol);
        // Agregar fecha de registro
        usuario.setFechaRegistro(Timestamp.from(Instant.now()));

        // Persistir el usuario y devolver ResponseDTO
        usuario = usuarioRepository.save(usuario);
        return UsuarioMapper.entityToResponseDTO(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDTO findById(Integer id) throws Exception {
    Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        return UsuarioMapper.entityToDto(usuario);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UsuarioResponseDTO updateUsuario(Integer id, UsuarioRequestDTO request) throws Exception {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
        if (request == null) throw new Exception("Datos inválidos");

        if ((request.getFirstName() != null && !request.getFirstName().isBlank()) || (request.getLastName() != null && !request.getLastName().isBlank())) {
            String fn = request.getFirstName() == null ? "" : request.getFirstName().trim();
            String ln = request.getLastName() == null ? "" : request.getLastName().trim();
            String nombre = (fn + " " + ln).trim();
            if (!nombre.isBlank()) usuario.setNombre(nombre);
        }
        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            if (!request.getEmail().equalsIgnoreCase(usuario.getEmail()) && usuarioRepository.existsByEmail(request.getEmail())) {
                throw new ConflictException("Ya existe un usuario con ese email");
            }
            usuario.setEmail(request.getEmail());
        }
        if (request.getPassword() != null && !request.getPassword().isBlank()) usuario.setContrasena(request.getPassword());
        if (request.getPhone() != null) usuario.setTelefono(request.getPhone());

        if (request.getRoleId() != null) {
            Rol rol = rolRepository.findById(request.getRoleId())
            .orElseThrow(() -> new NotFoundException("Rol no encontrado"));
            usuario.setRol(rol);
        }

        usuario = usuarioRepository.save(usuario);
        return UsuarioMapper.entityToDto(usuario);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUsuario(Integer id) throws Exception {
        if (!usuarioRepository.existsById(id)) {
            throw new NotFoundException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}
