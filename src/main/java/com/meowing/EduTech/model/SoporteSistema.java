package com.meowing.EduTech.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//** CLASE DEL MICROSERVICIO 2.- MONITOREO DEL SISTEMA **
@Entity
@Table(name = "SoporteSistema")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoporteSistema {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_soporte_sistema;

    /* Quitar este comentario cuando la clase Usuario esté creada en la carpeta model 
    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario id_usuario;
    */


    @Column(nullable = false)
    private LocalDateTime inicio_trabajo_incidencia;
    
}
