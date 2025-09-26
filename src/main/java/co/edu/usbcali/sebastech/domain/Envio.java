package co.edu.usbcali.sebastech.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "envios")
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_envio")
    private Integer idEnvio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", referencedColumnName = "id_pedidos")
    private Pedido pedido;

    @Column(name = "direccion_envio", columnDefinition = "TEXT", nullable = false)
    private String direccionEnvio;

    @Column(name = "empresa_envio", length = 50)
    private String empresaEnvio;

    @Column(name = "numero_guia", length = 100)
    private String numeroGuia;

    @Column(name = "fecha_envio")
    private Timestamp fechaEnvio;

    @Column(name = "fecha_entrega_estimada")
    private Timestamp fechaEntregaEstimada;

    @Column(name = "estado_envio", length = 30)
    private String estadoEnvio;
}
