package com.meowing.EduTech.service;

import com.meowing.EduTech.model.Usuario;
import com.meowing.EduTech.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuario(int id) {
        return usuarioRepository.findById(id).get();
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(int id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> obtenerUsuarioByTipoUsuario(Integer idTipoUsuario) {
        return usuarioRepository.findByTipoUsuario_IdTipoUsuario(idTipoUsuario);
    }

}