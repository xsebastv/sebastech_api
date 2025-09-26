package co.edu.usbcali.sebastech.controller;

import co.edu.usbcali.sebastech.dto.PedidoRequestDTO;
import co.edu.usbcali.sebastech.dto.PedidoResponseDTO;
import co.edu.usbcali.sebastech.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/pedido")
@RestController
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping("/all/object")
    public List<PedidoResponseDTO> obtenerPedidos() {
        return pedidoService.obtenerPedidos();
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> savePedido(@RequestBody PedidoRequestDTO request) throws Exception {
        return new ResponseEntity<>(pedidoService.savePedido(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(pedidoService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<co.edu.usbcali.sebastech.dto.MessageResponse> deletePedido(@PathVariable Integer id) throws Exception {
        pedidoService.deletePedido(id);
        return ResponseEntity.ok(new co.edu.usbcali.sebastech.dto.MessageResponse("Pedido eliminado correctamente"));
    }
}
