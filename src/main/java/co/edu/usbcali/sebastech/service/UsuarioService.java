package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.dto.UsuarioRequestDTO;
import co.edu.usbcali.sebastech.dto.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {
    List<String> obtenerUsuarios(); // por nombre
    List<UsuarioResponseDTO> obtenerUsuariosResponseDTO();
    UsuarioResponseDTO saveUsuario(UsuarioRequestDTO request) throws Exception;
    UsuarioResponseDTO findById(Integer id) throws Exception;
    UsuarioResponseDTO updateUsuario(Integer id, UsuarioRequestDTO request) throws Exception;
    void deleteUsuario(Integer id) throws Exception;
}
