package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.domain.Producto;
import co.edu.usbcali.sebastech.domain.Resena;
import co.edu.usbcali.sebastech.domain.Usuario;
import co.edu.usbcali.sebastech.dto.ResenaRequestDTO;
import co.edu.usbcali.sebastech.dto.ResenaResponseDTO;
import co.edu.usbcali.sebastech.dto.ResenaPatchDTO;
import co.edu.usbcali.sebastech.exception.BadRequestException;
import co.edu.usbcali.sebastech.exception.NotFoundException;
import co.edu.usbcali.sebastech.mapper.ResenaMapper;
import co.edu.usbcali.sebastech.repository.ProductoRepository;
import co.edu.usbcali.sebastech.repository.ResenaRepository;
import co.edu.usbcali.sebastech.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResenaServiceImpl implements ResenaService {

    private final ResenaRepository resenaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ResenaResponseDTO> obtenerResenas() {
        return ResenaMapper.entityToDtoList(resenaRepository.findAll());
    }

    @Override
    @Transactional
    public ResenaResponseDTO saveResena(ResenaRequestDTO request) throws Exception {
        if (request == null) throw new BadRequestException("No se puede asociar la reseña");
        if (request.getUsuarioId() == null || request.getProductoId() == null) throw new BadRequestException("usuarioId y productoId son obligatorios");
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId()).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        Producto producto = productoRepository.findById(request.getProductoId()).orElseThrow(() -> new NotFoundException("Producto no encontrado"));
        Resena resena = ResenaMapper.requestDtoToEntity(request, usuario, producto);
        resena = resenaRepository.save(resena);
        return ResenaMapper.entityToDto(resena);
    }

    @Override
    @Transactional
    public ResenaResponseDTO patchResena(Integer id, ResenaPatchDTO patchDTO) throws Exception {
        Resena resena = resenaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reseña no encontrada"));
        
        if (patchDTO == null) throw new BadRequestException("Datos inválidos");
        
        // Solo actualizar campos que no sean null
        if (patchDTO.getCalificacion() != null) {
            resena.setCalificacion(patchDTO.getCalificacion());
        }
        if (patchDTO.getComentario() != null) {
            resena.setComentario(patchDTO.getComentario());
        }
        if (patchDTO.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(patchDTO.getUsuarioId())
                    .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
            resena.setUsuario(usuario);
        }
        if (patchDTO.getProductoId() != null) {
            Producto producto = productoRepository.findById(patchDTO.getProductoId())
                    .orElseThrow(() -> new NotFoundException("Producto no encontrado"));
            resena.setProducto(producto);
        }
        
        resena = resenaRepository.save(resena);
        return ResenaMapper.entityToDto(resena);
    }

    @Override
    @Transactional
    public void deleteResena(Integer id) throws Exception {
        if (!resenaRepository.existsById(id)) throw new NotFoundException("Reseña no encontrada");
        resenaRepository.deleteById(id);
    }
}
