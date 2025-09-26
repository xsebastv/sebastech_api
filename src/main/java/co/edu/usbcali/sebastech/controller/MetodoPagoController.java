package co.edu.usbcali.sebastech.controller;

import co.edu.usbcali.sebastech.dto.MetodoPagoRequestDTO;
import co.edu.usbcali.sebastech.dto.MetodoPagoResponseDTO;
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

    @GetMapping("/all/object")
    public List<MetodoPagoResponseDTO> obtenerMetodos() {
        return metodoPagoService.obtenerMetodos();
    }

    @PostMapping
    public ResponseEntity<MetodoPagoResponseDTO> saveMetodo(@RequestBody MetodoPagoRequestDTO request) throws Exception {
        return new ResponseEntity<>(metodoPagoService.saveMetodo(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<co.edu.usbcali.sebastech.dto.MessageResponse> deleteMetodo(@PathVariable Integer id) throws Exception {
        metodoPagoService.deleteMetodo(id);
        return ResponseEntity.ok(new co.edu.usbcali.sebastech.dto.MessageResponse("Método de pago eliminado correctamente"));
    }
}
