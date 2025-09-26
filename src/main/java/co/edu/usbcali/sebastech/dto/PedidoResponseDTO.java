package co.edu.usbcali.sebastech.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Builder
@Getter
public class PedidoResponseDTO {
    private Integer id;
    private Integer usuarioId;
    private String usuarioNombre;
    private Timestamp fecha;
    private String estado;
    private BigDecimal total;
}
