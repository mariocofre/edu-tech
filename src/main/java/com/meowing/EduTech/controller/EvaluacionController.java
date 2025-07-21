package com.meowing.EduTech.controller;
import com.meowing.EduTech.model.Evaluacion;
import com.meowing.EduTech.service.EvaluacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//** CONTROLADOR DEL MICROSERVICIO 4.- EVALUACIONES Y SEGUIMIENTO**
@RestController
@RequestMapping("/api/v1/evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    


    @Operation(
        summary = "Crear una nueva evaluación",
        description = "Registra una evaluación nueva en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Evaluación creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<Evaluacion> crear(@RequestBody Evaluacion evaluacion) {
        Evaluacion nuevaEvaluacion = evaluacionService.guardarEvaluacion(evaluacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEvaluacion);
    }

    



    @Operation(
        summary = "Listar todas las evaluaciones",
        description = "Devuelve una lista de todas las evaluaciones registradas."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Evaluaciones encontradas"),
        @ApiResponse(responseCode = "204", description = "No hay evaluaciones registradas")
    })
    @GetMapping
    public ResponseEntity<List<Evaluacion>> listar() {
        List<Evaluacion> evaluaciones = evaluacionService.obtenerEvaluaciones();
        if (evaluaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(evaluaciones);
    }




    @Operation(
        summary = "Obtener evaluación por ID",
        description = "Devuelve una evaluación específica según su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Evaluación encontrada"),
        @ApiResponse(responseCode = "404", description = "Evaluación no encontrada")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<Evaluacion> obtenerEvaluacion(@PathVariable int id) {
        try{
            Evaluacion evaluacion = evaluacionService.obtenerEvaluacion(id);
            return ResponseEntity.ok(evaluacion);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }



    
    @Operation(
        summary = "Eliminar evaluación por ID",
        description = "Elimina una evaluación específica según su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Evaluación eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Evaluación no encontrada")
    })
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
