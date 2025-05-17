package com.meowing.EduTech.controller;
import com.meowing.EduTech.model.Evaluacion;
import com.meowing.EduTech.service.EvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//** CONTROLADOR DEL MICROSERVICIO 4.- EVALUACIONES Y SEGUIMIENTO**
@RestController
@RequestMapping("api/v1/evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    // Metodo crear evaluacion
    @PostMapping
    public ResponseEntity<Evaluacion> crear(@RequestBody Evaluacion evaluacion) {
        Evaluacion nuevaEvaluacion = evaluacionService.guardarEvaluacion(evaluacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEvaluacion);
    }

    // Metodo para listar las evaluaciones
    @GetMapping
    public ResponseEntity<List<Evaluacion>> listar() {
        List<Evaluacion> evaluaciones = evaluacionService.obtenerEvaluaciones();
        if (evaluaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(evaluaciones);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Evaluacion> obtenerEvaluacion(@PathVariable int id) {
        try{
            Evaluacion evaluacion = evaluacionService.obtenerEvaluacion(id);
            return ResponseEntity.ok(evaluacion);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        try {
            evaluacionService.eliminarEvaluacion(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
