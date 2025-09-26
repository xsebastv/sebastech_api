package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.dto.PedidoRequestDTO;
import co.edu.usbcali.sebastech.dto.PedidoResponseDTO;

import java.util.List;

public interface PedidoService {
    List<PedidoResponseDTO> obtenerPedidos();
    PedidoResponseDTO savePedido(PedidoRequestDTO request) throws Exception;
    PedidoResponseDTO findById(Integer id) throws Exception;
    void deletePedido(Integer id) throws Exception;
}
