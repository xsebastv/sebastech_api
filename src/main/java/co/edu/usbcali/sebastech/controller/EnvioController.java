package co.edu.usbcali.sebastech.controller;

import co.edu.usbcali.sebastech.dto.EnvioRequestDTO;
import co.edu.usbcali.sebastech.dto.EnvioResponseDTO;
import co.edu.usbcali.sebastech.dto.EnvioPatchDTO;
import co.edu.usbcali.sebastech.service.EnvioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/envio")
@RestController
@RequiredArgsConstructor
public class EnvioController {

    private final EnvioService envioService;

    @GetMapping("/all/object")
    public List<EnvioResponseDTO> obtenerEnvios() {
        return envioService.obtenerEnvios();
    }

    @PostMapping
    public ResponseEntity<EnvioResponseDTO> saveEnvio(@RequestBody EnvioRequestDTO request) throws Exception {
        return new ResponseEntity<>(envioService.saveEnvio(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<co.edu.usbcali.sebastech.dto.MessageResponse> deleteEnvio(@PathVariable Integer id) throws Exception {
        envioService.deleteEnvio(id);
        return ResponseEntity.ok(new co.edu.usbcali.sebastech.dto.MessageResponse("Envío eliminado correctamente"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EnvioResponseDTO> patchEnvio(@PathVariable Integer id, @RequestBody EnvioPatchDTO patchDTO) throws Exception {
        return ResponseEntity.ok(envioService.patchEnvio(id, patchDTO));
    }
}
