package co.edu.usbcali.sebastech.mapper;

import co.edu.usbcali.sebastech.domain.Categoria;
import co.edu.usbcali.sebastech.dto.CategoriaRequestDTO;
import co.edu.usbcali.sebastech.dto.CategoriaResponseDTO;

import java.util.List;

public class CategoriaMapper {
    public static CategoriaResponseDTO entityToDto(Categoria categoria) {
        return CategoriaResponseDTO.builder()
                .idCategoria(categoria.getIdCategoria())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .build();
    }
    public static List<CategoriaResponseDTO> entityToDtoList (List<Categoria> categorias){
        return categorias.stream().map(CategoriaMapper::entityToDto).toList();
    }
    public static Categoria requestDtoToEntity(CategoriaRequestDTO categoriaRequestDTO) {
        return Categoria.builder()
                .nombre(categoriaRequestDTO.getNombre())
                .descripcion(categoriaRequestDTO.getDescripcion())
                .build();
    }
}

