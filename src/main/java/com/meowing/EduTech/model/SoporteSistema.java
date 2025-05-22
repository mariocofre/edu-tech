package com.meowing.EduTech.model;

import java.time.LocalDateTime;


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
    private int idSoporteSistema;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;


    @Column
    private LocalDateTime inicioTrabajoIncidencia;

}
