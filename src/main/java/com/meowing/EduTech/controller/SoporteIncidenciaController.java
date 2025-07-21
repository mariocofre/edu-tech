package com.meowing.EduTech.controller;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.meowing.EduTech.model.SoporteIncidencia;
import com.meowing.EduTech.service.SoporteIncidenciaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/v1/incidencias")
public class SoporteIncidenciaController {


    @Autowired
    private SoporteIncidenciaService incidenciaService;

    @Operation(summary = "Crear una nueva incidencia", description = "Registra una nueva incidencia en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Incidencia creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<SoporteIncidencia> crearIncidencia(@RequestBody SoporteIncidencia incidencia) {
        SoporteIncidencia nuevaIncidencia = incidenciaService.guardarIncidencia(incidencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaIncidencia);
    }




    
    @Operation(summary = "Listar todas las incidencias", description = "Obtiene una lista de todas las incidencias registradas en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de incidencias encontrada"),
        @ApiResponse(responseCode = "204", description = "No hay incidencias registradas")
    })
    @GetMapping 
    public ResponseEntity<List<SoporteIncidencia>> listarIncidencias() {
        List<SoporteIncidencia> incidencias = incidenciaService.obtenerIncidencias();
        if (incidencias.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(incidencias);
        }
    }




    @Operation(summary = "Buscar incidencia por ID", description = "Obtiene una incidencia específica mediante su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Incidencia encontrada"),
        @ApiResponse(responseCode = "404", description = "Incidencia no encontrada")
    })
    @GetMapping("/buscar/{id}") 
    public ResponseEntity<SoporteIncidencia> obtenerPorId(@PathVariable int id) {
        try {
            SoporteIncidencia incidencia = incidenciaService.obtenerIncidenciaPorId(id);
            return ResponseEntity.ok(incidencia); // 200 OK con el objeto
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found si no existe
        }
    }




    @Operation(summary = "Actualizar una incidencia", description = "Actualiza una incidencia existente según su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Incidencia actualizada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Incidencia no encontrada"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PutMapping("/update/{id}")  
    public ResponseEntity<SoporteIncidencia> actualizarIncidencia(
            @PathVariable int id,
            @RequestBody SoporteIncidencia nuevaIncidencia) {

        SoporteIncidencia actualizada = incidenciaService.actualizarIncidencia(id, nuevaIncidencia);
        return ResponseEntity.ok(actualizada);
    }




    @Operation(summary = "Buscar incidencias por ID del soporte", description = "Obtiene todas las incidencias asociadas al ID de un soporte del sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de incidencias encontrada"),
        @ApiResponse(responseCode = "404", description = "No se encontraron incidencias para el ID de soporte")
    })
    @GetMapping("/buscarPorSoporte/{id}") 
    public ResponseEntity<List<SoporteIncidencia>> buscarPorSoporteId(@PathVariable Integer idSoporteSistema) {
        List<SoporteIncidencia> nuevoSoporte = incidenciaService.obtenerIncidenciaPorIdSoporte(idSoporteSistema);
        return ResponseEntity.ok(nuevoSoporte);
    }
}
