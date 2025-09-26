package co.edu.usbcali.sebastech.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class DetallePedidoResponseDTO {
    private Integer id;
    private Integer pedidoId;
    private Integer productoId;
    private String productoNombre;
    private Integer cantidad;
    private BigDecimal precioUnitario;
}
