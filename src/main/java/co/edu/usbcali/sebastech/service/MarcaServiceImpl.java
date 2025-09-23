package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.domain.Marca;
import co.edu.usbcali.sebastech.dto.MarcaRequestDTO;
import co.edu.usbcali.sebastech.dto.MarcaResponseDTO;
import co.edu.usbcali.sebastech.exception.BadRequestException;
import co.edu.usbcali.sebastech.exception.NotFoundException;
import co.edu.usbcali.sebastech.mapper.MarcaMapper;
import co.edu.usbcali.sebastech.repository.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarcaServiceImpl implements MarcaService {

    private final MarcaRepository marcaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<String> obtenerMarcas() {
        List<Marca> marcas = marcaRepository.findAll();
        List<String> nombres = new ArrayList<>();
        for (Marca m : marcas) nombres.add(m.getNombre());
        return nombres;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MarcaResponseDTO> obtenerMarcasResponseDTO() {
        List<Marca> marcas = marcaRepository.findAll();
        return MarcaMapper.entityToDtoList(marcas);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public MarcaResponseDTO saveMarca(MarcaRequestDTO request) throws Exception {
        if (request == null) throw new BadRequestException("No se puede asociar la marca");
        if (request.getNombre() == null || request.getNombre().isBlank()) throw new BadRequestException("El nombre es obligatorio");
        Marca marca = MarcaMapper.requestDtoToEntity(request);
        marca = marcaRepository.save(marca);
        return MarcaMapper.entityToDto(marca);
    }

    @Override
    @Transactional(readOnly = true)
    public MarcaResponseDTO findById(Integer id) throws Exception {
        Marca marca = marcaRepository.findById(id).orElseThrow(() -> new NotFoundException("Marca no encontrada"));
        return MarcaMapper.entityToDto(marca);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public MarcaResponseDTO updateMarca(Integer id, MarcaRequestDTO request) throws Exception {
        Marca marca = marcaRepository.findById(id).orElseThrow(() -> new NotFoundException("Marca no encontrada"));
        if (request == null) throw new BadRequestException("Datos inválidos");
        if (request.getNombre() != null && !request.getNombre().isBlank()) marca.setNombre(request.getNombre());
        if (request.getPaisOrigen() != null) marca.setPaisOrigen(request.getPaisOrigen());
        marca = marcaRepository.save(marca);
        return MarcaMapper.entityToDto(marca);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteMarca(Integer id) throws Exception {
        if (!marcaRepository.existsById(id)) throw new NotFoundException("Marca no encontrada");
        marcaRepository.deleteById(id);
    }
}
