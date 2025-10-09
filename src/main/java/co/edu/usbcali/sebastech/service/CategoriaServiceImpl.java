package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.domain.Categoria;
import co.edu.usbcali.sebastech.dto.CategoriaRequestDTO;
import co.edu.usbcali.sebastech.dto.CategoriaResponseDTO;
import co.edu.usbcali.sebastech.dto.CategoriaPatchDTO;
import co.edu.usbcali.sebastech.exception.BadRequestException;
import co.edu.usbcali.sebastech.exception.NotFoundException;
import co.edu.usbcali.sebastech.mapper.CategoriaMapper;
import co.edu.usbcali.sebastech.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<String> obtenerCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        List<String> listaCategorias = new ArrayList<>();
        for (Categoria categoria : categorias) {
            listaCategorias.add(categoria.getNombre());
        }
        return listaCategorias;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> obtenerCategoriasResponseDTO() {
        List<Categoria> categorias = categoriaRepository.findAll();
        List<CategoriaResponseDTO> responseDTOS = CategoriaMapper.entityToDtoList(categorias);
        return responseDTOS;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public CategoriaResponseDTO saveCategoria(CategoriaRequestDTO categoriaRequestDTO) throws Exception {
        if (categoriaRequestDTO == null) {
            throw new BadRequestException("No se puede asociar la categoría");
        }
        if (categoriaRequestDTO.getNombre() == null || categoriaRequestDTO.getNombre().isBlank()) {
            throw new BadRequestException("El nombre es obligatorio");
        }

        Categoria categoria = CategoriaMapper.requestDtoToEntity(categoriaRequestDTO);
        categoria = categoriaRepository.save(categoria);
        return CategoriaMapper.entityToDto(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaResponseDTO findById(Integer id) throws Exception {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));
        return CategoriaMapper.entityToDto(categoria);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CategoriaResponseDTO updateCategoria(Integer id, CategoriaRequestDTO categoriaRequestDTO) throws Exception {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));
        if (categoriaRequestDTO == null) throw new BadRequestException("Datos inválidos");
        if (categoriaRequestDTO.getNombre() != null && !categoriaRequestDTO.getNombre().isBlank()) {
            categoria.setNombre(categoriaRequestDTO.getNombre());
        }
        if (categoriaRequestDTO.getDescripcion() != null) {
            categoria.setDescripcion(categoriaRequestDTO.getDescripcion());
        }
        categoria = categoriaRepository.save(categoria);
        return CategoriaMapper.entityToDto(categoria);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CategoriaResponseDTO patchCategoria(Integer id, CategoriaPatchDTO patchDTO) throws Exception {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));
        
        if (patchDTO == null) throw new BadRequestException("Datos inválidos");
        
        // Solo actualizar campos que no sean null
        if (patchDTO.getNombre() != null && !patchDTO.getNombre().isBlank()) {
            categoria.setNombre(patchDTO.getNombre());
        }
        if (patchDTO.getDescripcion() != null) {
            categoria.setDescripcion(patchDTO.getDescripcion());
        }
        
        categoria = categoriaRepository.save(categoria);
        return CategoriaMapper.entityToDto(categoria);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCategoria(Integer id) throws Exception {
        if (!categoriaRepository.existsById(id)) {
            throw new NotFoundException("Categoría no encontrada");
        }
        categoriaRepository.deleteById(id);
    }
}

