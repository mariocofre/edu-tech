package com.meowing.EduTech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "CursoContenido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoContenido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_CursoContenido;

    @Column(nullable = false)
    private String encabezado;

    @Column(nullable = false)
    private String contenido;

    @Column(nullable = false)
    private Date fechaActualizacion;

    @ManyToOne
    @JoinColumn(name = "id_curso",nullable = false)
    private Curso curso;

}

