package co.edu.usbcali.sebastech.mapper;

import co.edu.usbcali.sebastech.domain.Marca;
import co.edu.usbcali.sebastech.dto.MarcaRequestDTO;
import co.edu.usbcali.sebastech.dto.MarcaResponseDTO;

import java.util.List;

public class MarcaMapper {
    public static MarcaResponseDTO entityToDto(Marca marca) {
        return MarcaResponseDTO.builder()
                .idMarca(marca.getIdMarca())
                .nombre(marca.getNombre())
                .paisOrigen(marca.getPaisOrigen())
                .build();
    }

    public static List<MarcaResponseDTO> entityToDtoList(List<Marca> marcas) {
        return marcas.stream().map(MarcaMapper::entityToDto).toList();
    }

    public static Marca requestDtoToEntity(MarcaRequestDTO dto) {
        return Marca.builder()
                .nombre(dto.getNombre())
                .paisOrigen(dto.getPaisOrigen())
                .build();
    }
}
