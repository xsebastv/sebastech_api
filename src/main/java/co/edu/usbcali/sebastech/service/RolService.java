package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.dto.RolRequestDTO;
import co.edu.usbcali.sebastech.dto.RolResponseDTO;

import java.util.List;

public interface RolService {
    List<String> obtenerRoles();
    List<RolResponseDTO> obtenerRolesResponseDTO();
    RolResponseDTO saveRol(RolRequestDTO rolRequestDTO) throws Exception;
    RolResponseDTO findById(Integer id) throws Exception;
    RolResponseDTO updateRol(Integer id, RolRequestDTO rolRequestDTO) throws Exception;
    void deleteRol(Integer id) throws Exception;
}