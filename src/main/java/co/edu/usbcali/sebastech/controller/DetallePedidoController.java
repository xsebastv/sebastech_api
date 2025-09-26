package co.edu.usbcali.sebastech.controller;

import co.edu.usbcali.sebastech.dto.DetallePedidoRequestDTO;
import co.edu.usbcali.sebastech.dto.DetallePedidoResponseDTO;
import co.edu.usbcali.sebastech.service.DetallePedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/detalle-pedido")
@RestController
@RequiredArgsConstructor
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    @GetMapping("/all/object")
    public List<DetallePedidoResponseDTO> obtenerDetalles() {
        return detallePedidoService.obtenerDetalles();
    }

    @PostMapping
    public ResponseEntity<DetallePedidoResponseDTO> saveDetalle(@RequestBody DetallePedidoRequestDTO request) throws Exception {
        return new ResponseEntity<>(detallePedidoService.saveDetalle(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<co.edu.usbcali.sebastech.dto.MessageResponse> deleteDetalle(@PathVariable Integer id) throws Exception {
        detallePedidoService.deleteDetalle(id);
        return ResponseEntity.ok(new co.edu.usbcali.sebastech.dto.MessageResponse("Detalle eliminado correctamente"));
    }
}
