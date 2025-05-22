package com.meowing.EduTech.service;

import com.meowing.EduTech.model.ComentarioForo;
import com.meowing.EduTech.repository.ComentarioForoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//** SERVICIO DEL MICROSERVICIO 5.- COMUNICACION Y FOROS**
@Service
@Transactional
public class ComentarioForoService {

    @Autowired
    private ComentarioForoRepository comentarioForoRepository;

    public List<ComentarioForo> getAll() {return comentarioForoRepository.findAll();}

    public ComentarioForo obtenerComentario(int id) {return comentarioForoRepository.findById(id).get();}

    public ComentarioForo crearComentario(ComentarioForo comentarioForo) {return comentarioForoRepository.save(comentarioForo);}

    public void eliminarComentario(int id) {comentarioForoRepository.deleteById(id);}

    // Obtener comentarios por id de foro
    public List<ComentarioForo> obtenerComentarioByForo(Integer idForo) {
        return comentarioForoRepository.findByForo_Idforo(idForo);
    }

    // Obteer comentarios por id de usuario
    public List<ComentarioForo> obtenerComentarioByUsuario(Integer idUsuario) {
        return comentarioForoRepository.findByUsuario_idUsuario(idUsuario);
    }

}
