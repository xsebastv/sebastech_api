package co.edu.usbcali.sebastech.service;

import co.edu.usbcali.sebastech.dto.ProductoRequestDTO;
import co.edu.usbcali.sebastech.dto.ProductoResponseDTO;
import co.edu.usbcali.sebastech.dto.ProductoPatchDTO;

import java.util.List;

public interface ProductoService {
    List<String> obtenerProductos();
    List<ProductoResponseDTO> obtenerProductosResponseDTO();
    ProductoResponseDTO saveProducto(ProductoRequestDTO request) throws Exception;
    ProductoResponseDTO findById(Integer id) throws Exception;
    ProductoResponseDTO updateProducto(Integer id, ProductoRequestDTO request) throws Exception;
    ProductoResponseDTO patchProducto(Integer id, ProductoPatchDTO patchDTO) throws Exception;
    void deleteProducto(Integer id) throws Exception;
}
