package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.dto.ResenaRequestDTO;
import co.edu.usbcali.sebastech.dto.ResenaResponseDTO;

import java.util.List;

public interface ResenaService {
    List<ResenaResponseDTO> obtenerResenas();
    ResenaResponseDTO saveResena(ResenaRequestDTO request) throws Exception;
    void deleteResena(Integer id) throws Exception;
}
