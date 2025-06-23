package com.meowing.EduTech.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meowing.EduTech.model.Evaluacion;
import com.meowing.EduTech.model.SoporteIncidencia;


//** CLASE DEL MICROSERVICIO 2.- MONITOREO DEL SISTEMA **
@Repository
public interface SoporteIncidenciaRepository extends JpaRepository<SoporteIncidencia, Integer> {

    List<SoporteIncidencia> findBySoporteSistema_IdSoporteSistema(Integer idSoporteSistema);

    // Comente la linea de abajo por que no tiene ninguna funcion y no permite ejecutar el codigo
    //List<SoporteIncidencia> findBy_IdSoporteIncidencia(int idSoporteIncidencia);
    
}
