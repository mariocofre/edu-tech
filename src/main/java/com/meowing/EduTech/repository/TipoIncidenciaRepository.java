package com.meowing.EduTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meowing.EduTech.model.SoporteSistema;
import com.meowing.EduTech.model.TipoIncidencia;

//** CLASE DEL MICROSERVICIO 2.- MONITOREO DEL SISTEMA **
@Repository
public interface TipoIncidenciaRepository extends JpaRepository<TipoIncidencia, Integer>{

}
