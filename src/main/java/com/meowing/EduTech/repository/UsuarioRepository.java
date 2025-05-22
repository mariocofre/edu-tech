package com.meowing.EduTech.repository;

import com.meowing.EduTech.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    //funcion para tener id_tipousuario a usuario
    List<Usuario> findByTipoUsuario_IdTipoUsuario(Integer idTipoUsuario);
}
