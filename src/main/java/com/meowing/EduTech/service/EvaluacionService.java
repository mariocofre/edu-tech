package com.meowing.EduTech.service;

import com.meowing.EduTech.model.Evaluacion;
import com.meowing.EduTech.repository.EvaluacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//** SERVICIO DEL MICROSERVICIO 4.- EVALUACIONES Y SEGUIMIENTO**
@Service
@Transactional
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    public List<Evaluacion> obtenerEvaluaciones() {
        return evaluacionRepository.findAll();
    }

    public Evaluacion obtenerEvaluacion(int id) {
        return evaluacionRepository.findById(id).get();
    }

    public Evaluacion guardarEvaluacion(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    public void eliminarEvaluacion(int id) {
        evaluacionRepository.deleteById(id);
    }
}
