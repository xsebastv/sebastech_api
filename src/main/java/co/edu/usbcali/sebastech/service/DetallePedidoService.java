package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.dto.DetallePedidoRequestDTO;
import co.edu.usbcali.sebastech.dto.DetallePedidoResponseDTO;

import java.util.List;

public interface DetallePedidoService {
    List<DetallePedidoResponseDTO> obtenerDetalles();
    DetallePedidoResponseDTO saveDetalle(DetallePedidoRequestDTO request) throws Exception;
    void deleteDetalle(Integer id) throws Exception;
}
