package com.meowing.EduTech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Seccion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Seccion;

    @Column(nullable = false)
    private String codigoSeccion;

    @Column(nullable = false)
    private String comentarios;

    @ManyToOne
    @JoinColumn(name = "id_curso",nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_usuario",nullable = false)
    private Usuario usuario;


}
