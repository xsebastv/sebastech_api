package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.dto.MetodoPagoRequestDTO;
import co.edu.usbcali.sebastech.dto.MetodoPagoResponseDTO;

import java.util.List;

public interface MetodoPagoService {
    List<MetodoPagoResponseDTO> obtenerMetodos();
    MetodoPagoResponseDTO saveMetodo(MetodoPagoRequestDTO request) throws Exception;
    void deleteMetodo(Integer id) throws Exception;
}
