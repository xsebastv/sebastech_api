package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.dto.MetodoPagoRequestDTO;
import co.edu.usbcali.sebastech.dto.MetodoPagoResponseDTO;
import co.edu.usbcali.sebastech.dto.MetodoPagoPatchDTO;

import java.util.List;

public interface MetodoPagoService {
    List<String> obtenerMetodosPago();
    List<MetodoPagoResponseDTO> obtenerMetodos();
    MetodoPagoResponseDTO obtenerMetodoPorId(Integer id) throws Exception;
    MetodoPagoResponseDTO saveMetodo(MetodoPagoRequestDTO request) throws Exception;
    MetodoPagoResponseDTO updateMetodo(Integer id, MetodoPagoRequestDTO request) throws Exception;
    MetodoPagoResponseDTO patchMetodoPago(Integer id, MetodoPagoPatchDTO patchDTO) throws Exception;
    void deleteMetodo(Integer id) throws Exception;
}
