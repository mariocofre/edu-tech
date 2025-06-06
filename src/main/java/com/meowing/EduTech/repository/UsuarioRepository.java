package com.meowing.EduTech.repository;

import com.meowing.EduTech.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    //funcion para tener id_tipousuario a usuario
    List<Usuario> findByTipoUsuario_IdTipoUsuario(Integer idTipoUsuario);
    Usuario findByRunUsuario(String runUsuario);
    @Transactional
    void deleteByRunUsuario(String runUsuario);
}
