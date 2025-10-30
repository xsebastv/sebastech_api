package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.dto.DetallePedidoRequestDTO;
import co.edu.usbcali.sebastech.dto.DetallePedidoResponseDTO;
import co.edu.usbcali.sebastech.dto.DetallePedidoPatchDTO;

import java.util.List;

public interface DetallePedidoService {
    List<DetallePedidoResponseDTO> obtenerDetalles();
    DetallePedidoResponseDTO saveDetalle(DetallePedidoRequestDTO request) throws Exception;
    DetallePedidoResponseDTO patchDetallePedido(Integer id, DetallePedidoPatchDTO patchDTO) throws Exception;
    void deleteDetalle(Integer id) throws Exception;
}
