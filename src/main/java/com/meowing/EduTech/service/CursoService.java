package com.meowing.EduTech.service;


import com.meowing.EduTech.model.Curso;
import com.meowing.EduTech.repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> obtenerCursos() {
        return cursoRepository.findAll();
    }

    public Curso obtenerCurso(int id) {
        return cursoRepository.findById(id).get();
    }

    public Curso guardarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void eliminarCurso(int id) {
        cursoRepository.deleteById(id);
    }
}