package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.dto.ResenaRequestDTO;
import co.edu.usbcali.sebastech.dto.ResenaResponseDTO;
import co.edu.usbcali.sebastech.dto.ResenaPatchDTO;

import java.util.List;

public interface ResenaService {
    List<ResenaResponseDTO> obtenerResenas();
    ResenaResponseDTO saveResena(ResenaRequestDTO request) throws Exception;
    ResenaResponseDTO patchResena(Integer id, ResenaPatchDTO patchDTO) throws Exception;
    void deleteResena(Integer id) throws Exception;
}
