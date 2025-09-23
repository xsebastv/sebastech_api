package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.dto.MarcaRequestDTO;
import co.edu.usbcali.sebastech.dto.MarcaResponseDTO;

import java.util.List;

public interface MarcaService {
    List<String> obtenerMarcas();
    List<MarcaResponseDTO> obtenerMarcasResponseDTO();
    MarcaResponseDTO saveMarca(MarcaRequestDTO request) throws Exception;
    MarcaResponseDTO findById(Integer id) throws Exception;
    MarcaResponseDTO updateMarca(Integer id, MarcaRequestDTO request) throws Exception;
    void deleteMarca(Integer id) throws Exception;
}
