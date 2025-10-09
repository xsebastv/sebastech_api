package co.edu.usbcali.sebastech.controller;

import co.edu.usbcali.sebastech.dto.UsuarioRequestDTO;
import co.edu.usbcali.sebastech.dto.UsuarioResponseDTO;
import co.edu.usbcali.sebastech.dto.UsuarioPatchDTO;
import co.edu.usbcali.sebastech.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;

@RequestMapping("/api/usuario")
@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/all")
    public List<String> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    @GetMapping("all/object")
    public List<UsuarioResponseDTO> obtenerUsuariosResponseDTO() {
        return usuarioService.obtenerUsuariosResponseDTO();
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> saveUsuario(@Valid @RequestBody UsuarioRequestDTO request) throws Exception {
        return new ResponseEntity<>(usuarioService.saveUsuario(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> updateUsuario(@PathVariable Integer id,
                                                            @Valid @RequestBody UsuarioRequestDTO request) throws Exception {
        return ResponseEntity.ok(usuarioService.updateUsuario(id, request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> patchUsuario(@PathVariable Integer id,
                                                           @RequestBody UsuarioPatchDTO patchDTO) throws Exception {
        return ResponseEntity.ok(usuarioService.patchUsuario(id, patchDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<co.edu.usbcali.sebastech.dto.MessageResponse> deleteUsuario(@PathVariable Integer id) throws Exception {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.ok(new co.edu.usbcali.sebastech.dto.MessageResponse("Usuario eliminado correctamente"));
    }
}
