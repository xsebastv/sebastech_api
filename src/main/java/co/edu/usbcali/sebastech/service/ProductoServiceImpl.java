package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.domain.Categoria;
import co.edu.usbcali.sebastech.domain.Marca;
import co.edu.usbcali.sebastech.domain.Producto;
import co.edu.usbcali.sebastech.dto.ProductoRequestDTO;
import co.edu.usbcali.sebastech.dto.ProductoResponseDTO;
import co.edu.usbcali.sebastech.dto.ProductoPatchDTO;
import co.edu.usbcali.sebastech.exception.BadRequestException;
import co.edu.usbcali.sebastech.exception.NotFoundException;
import co.edu.usbcali.sebastech.mapper.ProductoMapper;
import co.edu.usbcali.sebastech.repository.CategoriaRepository;
import co.edu.usbcali.sebastech.repository.MarcaRepository;
import co.edu.usbcali.sebastech.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final MarcaRepository marcaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<String> obtenerProductos() {
        List<Producto> productos = productoRepository.findAll();
        List<String> nombres = new ArrayList<>();
        for (Producto p : productos) nombres.add(p.getNombre());
        return nombres;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> obtenerProductosResponseDTO() {
        return ProductoMapper.entityToDtoList(productoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ProductoResponseDTO saveProducto(ProductoRequestDTO request) throws Exception {
        if (request == null) throw new BadRequestException("No se puede asociar el producto");
        if (request.getNombre() == null || request.getNombre().isBlank()) throw new BadRequestException("El nombre es obligatorio");
        if (request.getPrecio() == null) throw new BadRequestException("El precio es obligatorio");
        if (request.getStock() == null) throw new BadRequestException("El stock es obligatorio");

        Categoria categoria = null;
        if (request.getCategoriaId() != null) {
            categoria = categoriaRepository.findById(request.getCategoriaId())
                    .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));
        }
        Marca marca = null;
        if (request.getMarcaId() != null) {
            marca = marcaRepository.findById(request.getMarcaId())
                    .orElseThrow(() -> new NotFoundException("Marca no encontrada"));
        }

        Producto producto = ProductoMapper.requestDtoToEntity(request, categoria, marca);
        producto = productoRepository.save(producto);
        return ProductoMapper.entityToDto(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoResponseDTO findById(Integer id) throws Exception {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new NotFoundException("Producto no encontrado"));
        return ProductoMapper.entityToDto(producto);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ProductoResponseDTO updateProducto(Integer id, ProductoRequestDTO request) throws Exception {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new NotFoundException("Producto no encontrado"));
        if (request == null) throw new BadRequestException("Datos inválidos");
        if (request.getNombre() != null && !request.getNombre().isBlank()) producto.setNombre(request.getNombre());
        if (request.getDescripcion() != null) producto.setDescripcion(request.getDescripcion());
        if (request.getPrecio() != null) producto.setPrecio(request.getPrecio());
        if (request.getStock() != null) producto.setStock(request.getStock());
        if (request.getImagenUrl() != null) producto.setImagenUrl(request.getImagenUrl());
        if (request.getTipoProducto() != null) producto.setTipoProducto(request.getTipoProducto());
        if (request.getEspecificaciones() != null) producto.setEspecificaciones(request.getEspecificaciones());

        if (request.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                    .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));
            producto.setCategoria(categoria);
        }
        if (request.getMarcaId() != null) {
            Marca marca = marcaRepository.findById(request.getMarcaId())
                    .orElseThrow(() -> new NotFoundException("Marca no encontrada"));
            producto.setMarca(marca);
        }

        producto = productoRepository.save(producto);
        return ProductoMapper.entityToDto(producto);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ProductoResponseDTO patchProducto(Integer id, ProductoPatchDTO patchDTO) throws Exception {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));
        
        if (patchDTO == null) throw new BadRequestException("Datos inválidos");
        
        // Solo actualizar campos que no sean null
        if (patchDTO.getNombre() != null && !patchDTO.getNombre().isBlank()) {
            producto.setNombre(patchDTO.getNombre());
        }
        if (patchDTO.getDescripcion() != null) {
            producto.setDescripcion(patchDTO.getDescripcion());
        }
        if (patchDTO.getPrecio() != null) {
            producto.setPrecio(patchDTO.getPrecio());
        }
        if (patchDTO.getStock() != null) {
            producto.setStock(patchDTO.getStock());
        }
        if (patchDTO.getImagenUrl() != null) {
            producto.setImagenUrl(patchDTO.getImagenUrl());
        }
        if (patchDTO.getEspecificaciones() != null) {
            producto.setEspecificaciones(patchDTO.getEspecificaciones());
        }
        if (patchDTO.getTipoProducto() != null) {
            producto.setTipoProducto(patchDTO.getTipoProducto());
        }
        if (patchDTO.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(patchDTO.getCategoriaId())
                    .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));
            producto.setCategoria(categoria);
        }
        if (patchDTO.getMarcaId() != null) {
            Marca marca = marcaRepository.findById(patchDTO.getMarcaId())
                    .orElseThrow(() -> new NotFoundException("Marca no encontrada"));
            producto.setMarca(marca);
        }
        
        producto = productoRepository.save(producto);
        return ProductoMapper.entityToDto(producto);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteProducto(Integer id) throws Exception {
        if (!productoRepository.existsById(id)) throw new NotFoundException("Producto no encontrado");
        productoRepository.deleteById(id);
    }
}
