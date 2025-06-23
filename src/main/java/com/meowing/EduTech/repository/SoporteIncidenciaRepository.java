package com.meowing.EduTech.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.meowing.EduTech.model.SoporteIncidencia;


//** CLASE DEL MICROSERVICIO 2.- MONITOREO DEL SISTEMA **
@Repository
public interface SoporteIncidenciaRepository extends JpaRepository<SoporteIncidencia, Integer> {

<<<<<<< HEAD
    List<SoporteIncidencia> findBySoporteSistema_IdSoporteSistema(int idSoporteSistema);  // ---->  El _ (guión bajo) se utiliza para acceder a campos dentro de objetos relacionados.
=======
    List<SoporteIncidencia> findBySoporteSistema_IdSoporteSistema(Integer idSoporteSistema);

    // Comente la linea de abajo por que no tiene ninguna funcion y no permite ejecutar el codigo
    //List<SoporteIncidencia> findBy_IdSoporteIncidencia(int idSoporteIncidencia);
>>>>>>> d0d38f5c71f556f31b151d214d148412c5342b1c
    
}
