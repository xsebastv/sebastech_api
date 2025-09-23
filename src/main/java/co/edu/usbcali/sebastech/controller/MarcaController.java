package co.edu.usbcali.sebastech.controller;

import co.edu.usbcali.sebastech.dto.MarcaRequestDTO;
import co.edu.usbcali.sebastech.dto.MarcaResponseDTO;
import co.edu.usbcali.sebastech.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/marca")
@RestController
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;

    @GetMapping("/all")
    public List<String> obtenerMarcas() {
        return marcaService.obtenerMarcas();
    }

    @GetMapping("all/object")
    public List<MarcaResponseDTO> obtenerMarcasResponseDTO() {
        return marcaService.obtenerMarcasResponseDTO();
    }

    @PostMapping
    public ResponseEntity<MarcaResponseDTO> saveMarca(@RequestBody MarcaRequestDTO request) throws Exception {
        return new ResponseEntity<>(marcaService.saveMarca(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaResponseDTO> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(marcaService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaResponseDTO> updateMarca(@PathVariable Integer id,
                                                        @RequestBody MarcaRequestDTO request) throws Exception {
        return ResponseEntity.ok(marcaService.updateMarca(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<co.edu.usbcali.sebastech.dto.MessageResponse> deleteMarca(@PathVariable Integer id) throws Exception {
        marcaService.deleteMarca(id);
        return ResponseEntity.ok(new co.edu.usbcali.sebastech.dto.MessageResponse("Marca eliminada correctamente"));
    }
}
