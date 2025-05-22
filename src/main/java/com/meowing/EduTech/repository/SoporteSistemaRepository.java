package com.meowing.EduTech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.meowing.EduTech.model.SoporteSistema;



//** CLASE DEL MICROSERVICIO 2.- MONITOREO DEL SISTEMA **
@Repository
public interface SoporteSistemaRepository extends JpaRepository<SoporteSistema, Integer>{

    // Esta función es para que Spring JPA entienda que usuario es el campo de la entidad 
    // SoporteSistema y permite que el idUsuario esté en SoporteSistema

    // Descomentar cuando la clase Usuario esté lista
    //List<SoporteSistema> findByUsuarioId(Integer Usuario);
    
}
