package com.meowing.EduTech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TipoUsuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoUsuario;

    @Column(nullable = false, length = 100)
    private String tipoUsuario;

}
