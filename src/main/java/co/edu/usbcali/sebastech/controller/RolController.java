package co.edu.usbcali.sebastech.controller;

import co.edu.usbcali.sebastech.dto.RolRequestDTO;
import co.edu.usbcali.sebastech.dto.RolResponseDTO;
import co.edu.usbcali.sebastech.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/rol")
@RestController
@RequiredArgsConstructor
public class RolController {
    private final RolService rolService;

    @GetMapping("/all")
    public List<String> obtenerRoles(){
        return rolService.obtenerRoles();
    }
    
    @GetMapping("all/object")
    public List<RolResponseDTO> obtenerRolesResponseDTO(){
        return rolService.obtenerRolesResponseDTO();
    }
    
    @PostMapping
    public ResponseEntity<RolResponseDTO> saveRol(
            @RequestBody RolRequestDTO rolRequestDTO) throws Exception {
        return new ResponseEntity<>
                (rolService.saveRol(rolRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolResponseDTO> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(rolService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolResponseDTO> updateRol(@PathVariable Integer id,
                                                    @RequestBody RolRequestDTO rolRequestDTO) throws Exception {
        return ResponseEntity.ok(rolService.updateRol(id, rolRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<co.edu.usbcali.sebastech.dto.MessageResponse> deleteRol(@PathVariable Integer id) throws Exception {
        rolService.deleteRol(id);
        return ResponseEntity.ok(new co.edu.usbcali.sebastech.dto.MessageResponse("Rol eliminado correctamente"));
    }
}