package com.meowing.EduTech.repository;


import com.meowing.EduTech.model.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//** REPOSITORIO DEL MICROSERVICIO 4.- EVALUACIONES Y SEGUIMIENTO**
@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Integer>{
    List<Evaluacion> findBySeccion_idSeccion(Integer idSeccion);
}
