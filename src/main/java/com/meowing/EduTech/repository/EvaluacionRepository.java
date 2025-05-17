package com.Meowing.Evaluaciones.repository;

import com.Meowing.Evaluaciones.model.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//** CLASE DEL MICROSERVICIO 4.- EVALUACIONES Y SEGUIMIENTO**
@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Integer>{
}
