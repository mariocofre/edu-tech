package com.meowing.EduTech.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
