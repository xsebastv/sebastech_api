package co.edu.usbcali.sebastech.controller;

import co.edu.usbcali.sebastech.dto.CategoriaRequestDTO;
import co.edu.usbcali.sebastech.dto.CategoriaResponseDTO;
import co.edu.usbcali.sebastech.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/categoria")
@RestController
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @GetMapping("/all")
    public List<String> obtenerCategorias(){
        return categoriaService.obtenerCategorias();
    }
    @GetMapping("all/object")
    public List<CategoriaResponseDTO> obtenerCategoriasResponseDTO(){
        return categoriaService.obtenerCategoriasResponseDTO();
    }
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> saveCategoria(
            @RequestBody CategoriaRequestDTO categoriaRequestDTO) throws Exception {
        return new ResponseEntity<>
                (categoriaService.saveCategoria(categoriaRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(categoriaService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> updateCategoria(@PathVariable Integer id,
                                                                @RequestBody CategoriaRequestDTO categoriaRequestDTO) throws Exception {
        return ResponseEntity.ok(categoriaService.updateCategoria(id, categoriaRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<co.edu.usbcali.sebastech.dto.MessageResponse> deleteCategoria(@PathVariable Integer id) throws Exception {
        categoriaService.deleteCategoria(id);
        return ResponseEntity.ok(new co.edu.usbcali.sebastech.dto.MessageResponse("Categoría eliminada correctamente"));
    }
}
