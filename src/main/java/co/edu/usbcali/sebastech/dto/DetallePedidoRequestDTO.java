package co.edu.usbcali.sebastech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoRequestDTO {
    private Integer pedidoId;
    private Integer productoId;
    private Integer cantidad;
    private BigDecimal precioUnitario;
}
