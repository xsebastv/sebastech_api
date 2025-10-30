package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.dto.CarritoRequestDTO;
import co.edu.usbcali.sebastech.dto.CarritoResponseDTO;
import co.edu.usbcali.sebastech.dto.CarritoPatchDTO;

import java.util.List;

public interface CarritoService {
    List<CarritoResponseDTO> obtenerCarrito();
    CarritoResponseDTO saveItem(CarritoRequestDTO request) throws Exception;
    CarritoResponseDTO patchCarrito(Integer id, CarritoPatchDTO patchDTO) throws Exception;
    void deleteItem(Integer id) throws Exception;
}
