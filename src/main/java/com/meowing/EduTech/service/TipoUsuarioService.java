package com.meowing.EduTech.service;

import com.meowing.EduTech.model.TipoUsuario;
import com.meowing.EduTech.repository.TipoUsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class TipoUsuarioService {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    public List<TipoUsuario> obtenerTipoUsuarios() {
        return tipoUsuarioRepository.findAll();
    }

    public TipoUsuario obtenerTipoUsuario(int id) {
        return tipoUsuarioRepository.findById(id).get();
    }

    public TipoUsuario guardarTipoUsuario(TipoUsuario tipoUsuario) {
        return tipoUsuarioRepository.save(tipoUsuario);
    }

    public void eliminarTipoUsuario(int id) {
        tipoUsuarioRepository.deleteById(id);
    }


}