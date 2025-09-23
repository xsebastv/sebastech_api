package co.edu.usbcali.sebastech.mapper;

import co.edu.usbcali.sebastech.domain.Rol;
import co.edu.usbcali.sebastech.dto.RolRequestDTO;
import co.edu.usbcali.sebastech.dto.RolResponseDTO;

import java.util.List;

public class RolMapper {
    public static RolResponseDTO entityToDto(Rol rol) {
        return RolResponseDTO.builder()
                .idRol(rol.getIdRol())
                .nombre(rol.getNombre())
                .descripcion(rol.getDescripcion())
                .build();
    }
    
    public static List<RolResponseDTO> entityToDtoList(List<Rol> roles) {
        return roles.stream().map(RolMapper::entityToDto).toList();
    }
    
    public static Rol requestDtoToEntity(RolRequestDTO rolRequestDTO) {
        return Rol.builder()
                .nombre(rolRequestDTO.getNombre())
                .descripcion(rolRequestDTO.getDescripcion())
                .build();
    }
}