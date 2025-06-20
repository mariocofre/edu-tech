package com.meowing.EduTech.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meowing.EduTech.model.Evaluacion;
import com.meowing.EduTech.model.SoporteIncidencia;


//** CLASE DEL MICROSERVICIO 2.- MONITOREO DEL SISTEMA **
@Repository
public interface SoporteIncidenciaRepository extends JpaRepository<SoporteIncidencia, Integer> {

    List<SoporteIncidencia> findBy_IdSoporteSistema(int idSoporteSistema);

    List<SoporteIncidencia> findBy_IdSoporteIncidencia(int idSoporteIncidencia);
    
}
