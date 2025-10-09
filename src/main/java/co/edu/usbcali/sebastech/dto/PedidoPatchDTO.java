package co.edu.usbcali.sebastech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoPatchDTO {
    private Integer usuarioId;
    private String estado;
    private BigDecimal total;
}