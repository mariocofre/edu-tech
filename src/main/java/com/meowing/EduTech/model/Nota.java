package com.Meowing.Evaluaciones.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//** CLASE DEL MICROSERVICIO 4.- EVALUACIONES Y SEGUIMIENTO**
@Entity
@Table(name = "Nota")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_nota;

    @Column(nullable = false)
    private Float nota;

    @OneToOne
    @JoinColumn(name = "id_evaluacion",nullable = false)
    private Evaluacion evaluacion;

    /* Remover comentario cuando Usuario se ecneuntre en models
    @ManyToOne
    @JoinColumn(name = "id_usuario",nullable = false)
    private Usuario usuario;
     */
}
