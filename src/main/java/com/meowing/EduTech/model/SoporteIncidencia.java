package com.meowing.EduTech.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Evaluacion")
@Data
@AllArgsConstructor
@NoArgsConstructor


//** CLASE DEL MICROSERVICIO 2.- MONITOREO DEL SISTEMA **
public class SoporteIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_soporte_incidencia;

    @Column(length = 1000, nullable = false)
    private String detalles;

    @Column(nullable = false)
    private LocalDateTime fecha_inicio_incidencia;

    @Column(nullable = false)
    private LocalDateTime fecha_termino_incidencia;

    
    @ManyToOne
    @JoinColumn(name = "id_soporte_sistema", nullable = false)
    private SoporteSistema id_soporte_sistema;


    /* Comentado hasta que cree la clase TipoIncidencia
    @OneToOne
    @JoinColumn(name = "id_tipo_incidencia", nullable = false)
    private TipoIncidencia id_tipo_incidencia;
    */
}