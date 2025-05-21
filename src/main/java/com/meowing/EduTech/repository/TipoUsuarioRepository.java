package com.meowing.EduTech.repository;

import com.meowing.EduTech.model.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {
}
