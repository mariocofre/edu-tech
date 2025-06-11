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

    @Column(nullable = false, length = 60)
    private String nombreUsuario;

    @Column(nullable = false, length = 60)
    private String apellidoUsuario;

    @Column(nullable = false, length = 20)
    private String passwordUsuario;

    @Column(nullable = false, length = 60)
    private String emailUsuario;

    @ManyToOne
    @JoinColumn(name = "id_tipo_usuario",nullable = false)
    private TipoUsuario tipoUsuario;
}