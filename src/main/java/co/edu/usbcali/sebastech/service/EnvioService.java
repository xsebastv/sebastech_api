package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.dto.EnvioRequestDTO;
import co.edu.usbcali.sebastech.dto.EnvioResponseDTO;
import co.edu.usbcali.sebastech.dto.EnvioPatchDTO;

import java.util.List;

public interface EnvioService {
    List<EnvioResponseDTO> obtenerEnvios();
    EnvioResponseDTO saveEnvio(EnvioRequestDTO request) throws Exception;
    EnvioResponseDTO patchEnvio(Integer id, EnvioPatchDTO patchDTO) throws Exception;
    void deleteEnvio(Integer id) throws Exception;
}
