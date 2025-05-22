package com.meowing.EduTech.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "SoporteIncidencia")
@Data
@AllArgsConstructor
@NoArgsConstructor


//** CLASE DEL MICROSERVICIO 2.- MONITOREO DEL SISTEMA **
public class SoporteIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSoporteIncidencia;

    @Column(length = 1000)
    private String detalles;

    @Column(nullable = false)
    private LocalDateTime fechaInicioIncidencia;

    @Column
    private LocalDateTime fechaTerminoIncidencia;

    @ManyToOne
    @JoinColumn(name = "id_soporte_sistema", unique = false)
    private SoporteSistema soporteSistema;

    @ManyToOne
    @JoinColumn(name = "id_tipo_incidencia", unique = false)
    private TipoIncidencia tipoIncidencia;
    
}