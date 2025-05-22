package com.meowing.EduTech.service;


import com.meowing.EduTech.model.Seccion;
import com.meowing.EduTech.repository.SeccionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SeccionService {
    @Autowired
    private SeccionRepository seccionRepository;

    public List<Seccion> obtenerSecciones() {
        return seccionRepository.findAll();
    }

    public Seccion obtenerSeccion(int id) {
        return seccionRepository.findById(id).get();
    }

    public Seccion guardarSeccion(Seccion seccion) {
        return seccionRepository.save(seccion);
    }

    public void eliminarSeccion(int id) {
        seccionRepository.deleteById(id);
    }

    public List<Seccion> obtenerSeccionByCurso(Integer idCurso) {
        return seccionRepository.findByCurso_IdCurso(idCurso);
    }

    public List<Seccion> obtenerSeccionByUsuario(Integer idUsuario) {
        return seccionRepository.findByUsuario_IdUsuario(idUsuario);
    }
}
