package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.domain.Categoria;
import co.edu.usbcali.sebastech.dto.CategoriaRequestDTO;
import co.edu.usbcali.sebastech.dto.CategoriaResponseDTO;

import java.util.List;

public interface CategoriaService {
    List<String> obtenerCategorias();
    List<CategoriaResponseDTO> obtenerCategoriasResponseDTO();
    CategoriaResponseDTO saveCategoria(CategoriaRequestDTO categoriaRequestDTO) throws  Exception;
    CategoriaResponseDTO findById(Integer id) throws Exception;
    CategoriaResponseDTO updateCategoria(Integer id, CategoriaRequestDTO categoriaRequestDTO) throws Exception;
    void deleteCategoria(Integer id) throws Exception;
}
