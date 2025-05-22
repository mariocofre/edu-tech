package com.meowing.EduTech.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "Pago")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Pago {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPago;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private String comprobante;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;
}
