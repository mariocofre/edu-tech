package com.meowing.EduTech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "TipoIncidencia")
@Data
@AllArgsConstructor
@NoArgsConstructor

//** CLASE DEL MICROSERVICIO 2.- MONITOREO DEL SISTEMA **
public class TipoIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoIncidencia ;
    

    @Column(length = 100, nullable = false, unique = false)
    private String tipo;

}
