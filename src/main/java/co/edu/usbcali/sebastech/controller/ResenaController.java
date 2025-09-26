package co.edu.usbcali.sebastech.controller;

import co.edu.usbcali.sebastech.dto.ResenaRequestDTO;
import co.edu.usbcali.sebastech.dto.ResenaResponseDTO;
import co.edu.usbcali.sebastech.service.ResenaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/resena")
@RestController
@RequiredArgsConstructor
public class ResenaController {

    private final ResenaService resenaService;

    @GetMapping("/all/object")
    public List<ResenaResponseDTO> obtenerResenas() {
        return resenaService.obtenerResenas();
    }

    @PostMapping
    public ResponseEntity<ResenaResponseDTO> saveResena(@RequestBody ResenaRequestDTO request) throws Exception {
        return new ResponseEntity<>(resenaService.saveResena(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<co.edu.usbcali.sebastech.dto.MessageResponse> deleteResena(@PathVariable Integer id) throws Exception {
        resenaService.deleteResena(id);
        return ResponseEntity.ok(new co.edu.usbcali.sebastech.dto.MessageResponse("Reseña eliminada correctamente"));
    }
}
