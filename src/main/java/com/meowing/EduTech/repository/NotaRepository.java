package com.Meowing.Evaluaciones.repository;

import com.Meowing.Evaluaciones.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//** CLASE DEL MICROSERVICIO 4.- EVALUACIONES Y SEGUIMIENTO**
@Repository
public interface NotaRepository extends JpaRepository<Nota, Integer>{
    /* Esta funcion es para que spring JPA entienda que usuario es el campo de la entidad notas
       y permite que el id usuario este en notas
    List<Nota> findByUsuarioId(Integer Usuario)
    */
}
