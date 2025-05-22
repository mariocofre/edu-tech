package com.meowing.EduTech.repository;


import com.meowing.EduTech.model.CursoContenido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoContenidoRepository extends JpaRepository<CursoContenido, Integer> {
    //funcion para tener id_tipousuario a usuario
    List<CursoContenido> findByCurso_IdCurso(Integer idCurso);
}
