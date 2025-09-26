package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.domain.Carrito;
import co.edu.usbcali.sebastech.domain.Producto;
import co.edu.usbcali.sebastech.domain.Usuario;
import co.edu.usbcali.sebastech.dto.CarritoRequestDTO;
import co.edu.usbcali.sebastech.dto.CarritoResponseDTO;
import co.edu.usbcali.sebastech.exception.BadRequestException;
import co.edu.usbcali.sebastech.exception.NotFoundException;
import co.edu.usbcali.sebastech.mapper.CarritoMapper;
import co.edu.usbcali.sebastech.repository.CarritoRepository;
import co.edu.usbcali.sebastech.repository.ProductoRepository;
import co.edu.usbcali.sebastech.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarritoServiceImpl implements CarritoService {

    private final CarritoRepository carritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CarritoResponseDTO> obtenerCarrito() {
        return CarritoMapper.entityToDtoList(carritoRepository.findAll());
    }

    @Override
    @Transactional
    public CarritoResponseDTO saveItem(CarritoRequestDTO request) throws Exception {
        if (request == null) throw new BadRequestException("No se puede asociar el item de carrito");
        if (request.getUsuarioId() == null || request.getProductoId() == null) throw new BadRequestException("usuarioId y productoId son obligatorios");
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId()).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        Producto producto = productoRepository.findById(request.getProductoId()).orElseThrow(() -> new NotFoundException("Producto no encontrado"));
        Carrito carrito = CarritoMapper.requestDtoToEntity(request, usuario, producto);
        carrito = carritoRepository.save(carrito);
        return CarritoMapper.entityToDto(carrito);
    }

    @Override
    @Transactional
    public void deleteItem(Integer id) throws Exception {
        if (!carritoRepository.existsById(id)) throw new NotFoundException("Item de carrito no encontrado");
        carritoRepository.deleteById(id);
    }
}
