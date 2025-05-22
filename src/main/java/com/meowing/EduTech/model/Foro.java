package com.meowing.EduTech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//** CLASE DEL MICROSERVICIO 5.- COMUNICACION Y FOROS**
@Entity
@Table(name = "Foro")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Foro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idforo;

<<<<<<< HEAD
    @OneToOne
    @JoinColumn(name = "id_seccion")
    private Seccion seccion;
=======

    @OneToOne
    @JoinColumn(name = "id_seccion")
    private Seccion seccion;

>>>>>>> 2e3e8ac78a633a779fb688d5cbfd58cf9292b9c2
}
