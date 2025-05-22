package com.meowing.EduTech.repository;

import com.meowing.EduTech.model.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeccionRepository extends JpaRepository<Seccion, Integer> {
    //funcion para tener id_curso a seccion
    List<Seccion> findByCurso_IdCurso(Integer idCurso);

    //funcion para tener id_Usuario a seccion
    List<Seccion> findByUsuario_IdUsuario(Integer idUsuario);
}