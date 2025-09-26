package co.edu.usbcali.sebastech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequestDTO {
    private Integer usuarioId;
    private String estado; // opcional
    private BigDecimal total; // opcional, se puede calcular por detalle
}
