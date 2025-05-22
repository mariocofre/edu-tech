package com.meowing.EduTech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//** CLASE DEL MICROSERVICIO 4.- EVALUACIONES Y SEGUIMIENTO**
@Entity
@Table(name = "Evaluacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Evaluacion;

    @Column(length = 100, nullable = false)
    private String tema;

    @Column(nullable = false)
    private Date fecha_Evaluacion;

    
    @OneToOne
    @JoinColumn(name = "id_seccion",nullable = false)
    private Seccion seccion;
    
}
