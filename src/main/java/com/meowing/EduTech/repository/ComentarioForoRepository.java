package com.meowing.EduTech.repository;

import com.meowing.EduTech.model.ComentarioForo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//** REPOSITORIO DEL MICROSERVICIO 5.- COMUNICACION Y FOROS**
@Repository
public interface ComentarioForoRepository extends JpaRepository<ComentarioForo, Integer> {

    //Funcion para anlcar id de foro a comentario foros
    List<ComentarioForo> findByForo_Idforo(Integer idForo);

    //Funcion para anclar id de usuario a comentario foros
    List<ComentarioForo> findByUsuario_idUsuario(Integer idUsuario);

}
