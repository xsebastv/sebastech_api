package co.edu.usbcali.sebastech.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedidos")
    private Integer idPedidos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @Column(name = "fecha")
    private Timestamp fecha;

    @Column(name = "estado", length = 30)
    private String estado;

    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total;
}
