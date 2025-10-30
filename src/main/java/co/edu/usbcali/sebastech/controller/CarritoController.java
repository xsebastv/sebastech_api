package co.edu.usbcali.sebastech.controller;

import co.edu.usbcali.sebastech.dto.CarritoRequestDTO;
import co.edu.usbcali.sebastech.dto.CarritoResponseDTO;
import co.edu.usbcali.sebastech.dto.CarritoPatchDTO;
import co.edu.usbcali.sebastech.service.CarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/carrito")
@RestController
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;

    @GetMapping("/all/object")
    public List<CarritoResponseDTO> obtenerCarrito() {
        return carritoService.obtenerCarrito();
    }

    @PostMapping
    public ResponseEntity<CarritoResponseDTO> saveItem(@RequestBody CarritoRequestDTO request) throws Exception {
        return new ResponseEntity<>(carritoService.saveItem(request), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CarritoResponseDTO> patchCarrito(@PathVariable Integer id,
                                                           @RequestBody CarritoPatchDTO patchDTO) throws Exception {
        return ResponseEntity.ok(carritoService.patchCarrito(id, patchDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<co.edu.usbcali.sebastech.dto.MessageResponse> deleteItem(@PathVariable Integer id) throws Exception {
        carritoService.deleteItem(id);
        return ResponseEntity.ok(new co.edu.usbcali.sebastech.dto.MessageResponse("Item eliminado del carrito correctamente"));
    }
}
