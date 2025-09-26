package co.edu.usbcali.sebastech.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class ProductoResponseDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;
    private String imagenUrl;
    private String categoriaNombre;
    private Integer categoriaId;
    private String marcaNombre;
    private Integer marcaId;
    private String especificaciones; // JSON string
    private String tipoProducto;
}
