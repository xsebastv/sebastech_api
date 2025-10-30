package co.edu.usbcali.sebastech.controller;

import co.edu.usbcali.sebastech.dto.MetodoPagoRequestDTO;
import co.edu.usbcali.sebastech.dto.MetodoPagoResponseDTO;
import co.edu.usbcali.sebastech.dto.MetodoPagoPatchDTO;
import co.edu.usbcali.sebastech.service.MetodoPagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/metodo-pago")
@RestController
@RequiredArgsConstructor
public class MetodoPagoController {

    private final MetodoPagoService metodoPagoService;

    @GetMapping("/all")
    public List<String> obtenerMetodosPago() {
        return metodoPagoService.obtenerMetodosPago();
    }

    @GetMapping("/all/object")
    public List<MetodoPagoResponseDTO> obtenerMetodos() {
        return metodoPagoService.obtenerMetodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPagoResponseDTO> obtenerMetodoPorId(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(metodoPagoService.obtenerMetodoPorId(id));
    }

    @PostMapping
    public ResponseEntity<MetodoPagoResponseDTO> saveMetodo(@RequestBody MetodoPagoRequestDTO request) throws Exception {
        return new ResponseEntity<>(metodoPagoService.saveMetodo(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPagoResponseDTO> updateMetodo(@PathVariable Integer id, @RequestBody MetodoPagoRequestDTO request) throws Exception {
        return ResponseEntity.ok(metodoPagoService.updateMetodo(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<co.edu.usbcali.sebastech.dto.MessageResponse> deleteMetodo(@PathVariable Integer id) throws Exception {
        metodoPagoService.deleteMetodo(id);
        return ResponseEntity.ok(new co.edu.usbcali.sebastech.dto.MessageResponse("Método de pago eliminado correctamente"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MetodoPagoResponseDTO> patchMetodo(@PathVariable Integer id, @RequestBody MetodoPagoPatchDTO patchDTO) throws Exception {
        return ResponseEntity.ok(metodoPagoService.patchMetodoPago(id, patchDTO));
    }
}
