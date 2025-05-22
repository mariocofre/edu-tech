package com.meowing.EduTech.model;


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

    @ManyToOne
    @JoinColumn(name = "id_evaluacion",nullable = false)
    private Evaluacion evaluacion;


    @ManyToOne
    @JoinColumn(name = "id_usuario",nullable = false)
    private Usuario usuario;
<<<<<<< HEAD
=======

>>>>>>> 2e3e8ac78a633a779fb688d5cbfd58cf9292b9c2
}
