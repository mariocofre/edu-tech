package com.meowing.EduTech.repository;

import com.meowing.EduTech.model.Foro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//** REPOSITORIO DEL MICROSERVICIO 5.- COMUNICACION Y FOROS**
@Repository
public interface ForoRepository extends JpaRepository<Foro, Integer> {

    //Funcion para anclar id de seccion a foro
    /*
    List<Foro> findByidSeccion(Integer idSeccion);
     */
}
