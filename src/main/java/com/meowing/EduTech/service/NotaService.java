package com.meowing.EduTech.service;



import com.meowing.EduTech.model.Nota;
import com.meowing.EduTech.repository.NotaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//** SERVICIO DEL MICROSERVICIO 4.- EVALUACIONES Y SEGUIMIENTO**
@Service
@Transactional
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    public List<Nota> obtenerNotas() {
        return notaRepository.findAll();
    }

    public Nota obtenerNota(int id) {
        return notaRepository.findById(id).get();
    }

    public Nota subirNota(Nota nota) {
        return notaRepository.save(nota);
    }

    public List<Nota> obtenerNotasPorUsuario(Integer idUsuario) {
        return notaRepository.findByUsuario_idUsuario(idUsuario);
    }
}
