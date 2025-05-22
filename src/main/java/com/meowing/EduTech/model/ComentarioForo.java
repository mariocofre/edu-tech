package com.meowing.EduTech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//** CLASE DEL MICROSERVICIO 5.- COMUNICACION Y FOROS**
@Entity
@Table(name = "ComentarioForo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioForo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_comentario_foro;

    @Column(length = 100, nullable = false)
    private String encabezado;

    @Column(length = 1000, nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private Date fecha_publicacion;

    @ManyToOne
    @JoinColumn(name = "id_foro", nullable = false)
    private Foro foro;

<<<<<<< HEAD
 
=======

>>>>>>> 2e3e8ac78a633a779fb688d5cbfd58cf9292b9c2
    @ManyToOne
    @JoinColumn(name = "id_usuario",nullable = false)
    private Usuario usuario;

}
