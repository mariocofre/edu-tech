package com.meowing.EduTech.service;


import com.meowing.EduTech.model.CursoContenido;
import com.meowing.EduTech.repository.CursoContenidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CursoContenidoService {
    @Autowired
    private CursoContenidoRepository cursoContenidoRepository;

    public List<CursoContenido> obtenerCursoContenidos() {
        return cursoContenidoRepository.findAll();
    }

    public CursoContenido obtenerCursoContenido(int id) {
        return cursoContenidoRepository.findById(id).get();
    }

    public CursoContenido guardarCursoContenido(CursoContenido cursoContenido) {
        return cursoContenidoRepository.save(cursoContenido);
    }

    public void eliminarCursoContenido(int id) {
        cursoContenidoRepository.deleteById(id);
    }

    public List<CursoContenido> obtenerCursoContenidoByCurso(Integer idCurso) {
        return cursoContenidoRepository.findByCurso_IdCurso(idCurso);
    }
}
