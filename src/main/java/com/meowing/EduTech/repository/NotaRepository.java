package com.meowing.EduTech.repository;

import com.meowing.EduTech.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
//** REPOSITORIO DEL MICROSERVICIO 4.- EVALUACIONES Y SEGUIMIENTO**
@Repository
public interface NotaRepository extends JpaRepository<Nota, Integer>{

    List<Nota> findByUsuario_idUsuario(Integer idUsuario);
}
