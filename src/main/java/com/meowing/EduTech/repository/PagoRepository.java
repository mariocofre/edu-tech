package com.meowing.EduTech.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meowing.EduTech.model.Pago;



@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer>{

    // Busca por idUsuario
    List<Pago> findByUsuario_idUsuario(Integer idUsuario);

    // Busca por idCurso
    List<Pago> findByCurso_idCurso (Integer idCurso);

}
