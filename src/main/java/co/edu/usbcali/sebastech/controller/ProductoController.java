package co.edu.usbcali.sebastech.controller;

import co.edu.usbcali.sebastech.dto.ProductoRequestDTO;
import co.edu.usbcali.sebastech.dto.ProductoResponseDTO;
import co.edu.usbcali.sebastech.dto.ProductoPatchDTO;
import co.edu.usbcali.sebastech.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/producto")
@RestController
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping("/all")
    public List<String> obtenerProductos() {
        return productoService.obtenerProductos();
    }

    @GetMapping("all/object")
    public List<ProductoResponseDTO> obtenerProductosResponseDTO() {
        return productoService.obtenerProductosResponseDTO();
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> saveProducto(@RequestBody ProductoRequestDTO request) throws Exception {
        return new ResponseEntity<>(productoService.saveProducto(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(productoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> updateProducto(@PathVariable Integer id,
                                                              @RequestBody ProductoRequestDTO request) throws Exception {
        return ResponseEntity.ok(productoService.updateProducto(id, request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> patchProducto(@PathVariable Integer id,
                                                             @RequestBody ProductoPatchDTO patchDTO) throws Exception {
        return ResponseEntity.ok(productoService.patchProducto(id, patchDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<co.edu.usbcali.sebastech.dto.MessageResponse> deleteProducto(@PathVariable Integer id) throws Exception {
        productoService.deleteProducto(id);
        return ResponseEntity.ok(new co.edu.usbcali.sebastech.dto.MessageResponse("Producto eliminado correctamente"));
    }
}
