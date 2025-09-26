package co.edu.usbcali.sebastech.mapper;

import co.edu.usbcali.sebastech.domain.Categoria;
import co.edu.usbcali.sebastech.domain.Marca;
import co.edu.usbcali.sebastech.domain.Producto;
import co.edu.usbcali.sebastech.dto.ProductoRequestDTO;
import co.edu.usbcali.sebastech.dto.ProductoResponseDTO;

import java.util.List;

public class ProductoMapper {
    public static ProductoResponseDTO entityToDto(Producto p) {
        Integer categoriaId = null; String categoriaNombre = null;
        Integer marcaId = null; String marcaNombre = null;
        if (p.getCategoria() != null) { categoriaId = p.getCategoria().getIdCategoria(); categoriaNombre = p.getCategoria().getNombre(); }
        if (p.getMarca() != null) { marcaId = p.getMarca().getIdMarca(); marcaNombre = p.getMarca().getNombre(); }
        return ProductoResponseDTO.builder()
                .id(p.getIdProductos())
                .nombre(p.getNombre())
                .descripcion(p.getDescripcion())
                .precio(p.getPrecio())
                .stock(p.getStock())
                .imagenUrl(p.getImagenUrl())
                .categoriaId(categoriaId)
                .categoriaNombre(categoriaNombre)
                .marcaId(marcaId)
                .marcaNombre(marcaNombre)
                .especificaciones(p.getEspecificaciones())
                .tipoProducto(p.getTipoProducto())
                .build();
    }

    public static List<ProductoResponseDTO> entityToDtoList(List<Producto> productos) {
        return productos.stream().map(ProductoMapper::entityToDto).toList();
    }

    public static Producto requestDtoToEntity(ProductoRequestDTO dto, Categoria categoria, Marca marca) {
        return Producto.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .stock(dto.getStock())
                .imagenUrl(dto.getImagenUrl())
                .categoria(categoria)
                .marca(marca)
                .especificaciones(dto.getEspecificaciones())
                .tipoProducto(dto.getTipoProducto())
                .build();
    }
}
