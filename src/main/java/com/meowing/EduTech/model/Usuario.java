package com.meowing.EduTech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(unique = true, length = 13, nullable = false)
    private String runUsuario;

    @Column(nullable = false)
    private String nombreUsuario;

    @Column(nullable = false)
    private String apellidoUsuario;

    @Column(nullable = false)
    private String passwordUsuario;

    @Column(nullable = false)
    private String emailUsuario;

    @ManyToOne
    @JoinColumn(name = "id_tipousuario",nullable = false)
    private TipoUsuario tipoUsuario;
}