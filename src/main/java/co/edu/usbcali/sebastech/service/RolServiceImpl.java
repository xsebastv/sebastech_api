package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.domain.Rol;
import co.edu.usbcali.sebastech.dto.RolRequestDTO;
import co.edu.usbcali.sebastech.dto.RolResponseDTO;
import co.edu.usbcali.sebastech.exception.BadRequestException;
import co.edu.usbcali.sebastech.exception.NotFoundException;
import co.edu.usbcali.sebastech.mapper.RolMapper;
import co.edu.usbcali.sebastech.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {
    private final RolRepository rolRepository;

    @Override
    @Transactional(readOnly = true)
    public List<String> obtenerRoles() {
        List<Rol> roles = rolRepository.findAll();
        List<String> listaRoles = new ArrayList<>();
        for (Rol rol : roles) {
            listaRoles.add(rol.getNombre());
        }
        return listaRoles;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RolResponseDTO> obtenerRolesResponseDTO() {
        List<Rol> roles = rolRepository.findAll();
        List<RolResponseDTO> responseDTOS = RolMapper.entityToDtoList(roles);
        return responseDTOS;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public RolResponseDTO saveRol(RolRequestDTO rolRequestDTO) throws Exception {
        if (rolRequestDTO == null) {
            throw new BadRequestException("No se puede asociar el rol");
        }
        if (rolRequestDTO.getNombre() == null || rolRequestDTO.getNombre().isBlank()) {
            throw new BadRequestException("El nombre del rol es obligatorio");
        }

        Rol rol = RolMapper.requestDtoToEntity(rolRequestDTO);
        rol = rolRepository.save(rol);
        return RolMapper.entityToDto(rol);
    }

    @Override
    @Transactional(readOnly = true)
    public RolResponseDTO findById(Integer id) throws Exception {
    Rol rol = rolRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Rol no encontrado"));
        return RolMapper.entityToDto(rol);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RolResponseDTO updateRol(Integer id, RolRequestDTO rolRequestDTO) throws Exception {
    Rol rol = rolRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Rol no encontrado"));
    if (rolRequestDTO == null) throw new BadRequestException("Datos inválidos");
        if (rolRequestDTO.getNombre() != null && !rolRequestDTO.getNombre().isBlank()) {
            rol.setNombre(rolRequestDTO.getNombre());
        }
        if (rolRequestDTO.getDescripcion() != null) {
            rol.setDescripcion(rolRequestDTO.getDescripcion());
        }
        rol = rolRepository.save(rol);
        return RolMapper.entityToDto(rol);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRol(Integer id) throws Exception {
        if (!rolRepository.existsById(id)) {
            throw new NotFoundException("Rol no encontrado");
        }
        rolRepository.deleteById(id);
    }
}