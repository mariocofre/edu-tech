package com.meowing.EduTech.service;

import com.meowing.EduTech.model.Evaluacion;
import com.meowing.EduTech.model.Seccion;
import com.meowing.EduTech.repository.EvaluacionRepository;
import com.meowing.EduTech.repository.SeccionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//** SERVICIO DEL MICROSERVICIO 4.- EVALUACIONES Y SEGUIMIENTO**
@Service
@Transactional
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private SeccionRepository seccionRepository; // Inyecto este repositorio para evitar errores

    public List<Evaluacion> obtenerEvaluaciones() {
        return evaluacionRepository.findAll();
    }

    public Evaluacion obtenerEvaluacion(int id) {
        return evaluacionRepository.findById(id).get();
    }

    public Evaluacion guardarEvaluacion(Evaluacion evaluacion) {
        // Codigo para resolver TransientPropertyValueException:

        // 1. Obtener el ID de la Seccion del objeto Evaluacion recibido
        Integer idSeccion = null;
        if (evaluacion.getSeccion() != null) {
            idSeccion = evaluacion.getSeccion().getIdSeccion();
        }

        // 2. Validar que el ID de la Seccion no sea nulo
        if (idSeccion == null) {
            throw new IllegalArgumentException("El ID de la sección es requerido para crear una evaluación.");
        }

        // 3. Buscar la Seccion existente en la base de datos usando el repositorio de Seccion
        // Esto recupera una instancia de Seccion que está "manejada" por Hibernate
        Optional<Seccion> seccionOptional = seccionRepository.findById(idSeccion);

        if (seccionOptional.isPresent()) {
            // 4. Si la Seccion existe, asigna esta instancia manejada a la Evaluacion
            evaluacion.setSeccion(seccionOptional.get());
        } else {
            // 5. Si la Seccion no existe, lanza una excepción para indicar el problema
            throw new EntityNotFoundException("No se encontró la Sección con ID: " + idSeccion + ". La evaluación no puede ser creada.");
        }

        // Este error ocurria ya que el hibernate maneja el objeto que recibe del post
        // En objetos que se suben con datos en null provoca errores
        return evaluacionRepository.save(evaluacion);
    }

    public void eliminarEvaluacion(int id) {
        evaluacionRepository.deleteById(id);
    }
}
