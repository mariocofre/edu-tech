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

import com.meowing.EduTech.model.Evaluacion;
import com.meowing.EduTech.model.SoporteIncidencia;
import com.meowing.EduTech.service.SoporteIncidenciaService;


@RestController
@RequestMapping("api/v1/incidencias")
public class SoporteIncidenciaController {


    @Autowired
    private SoporteIncidenciaService incidenciaService;

    // Crea una incidencia
    @PostMapping
    public ResponseEntity<SoporteIncidencia> crearIncidencia(@RequestBody SoporteIncidencia incidencia) {
        SoporteIncidencia nuevaIncidencia = incidenciaService.guardarIncidencia(incidencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaIncidencia);
    }



    // Listar incidencias del sistema
    @GetMapping // ENDPOINT OK
    public ResponseEntity<List<SoporteIncidencia>> listarIncidencias() {
        List<SoporteIncidencia> incidencias = incidenciaService.obtenerIncidencias();
        if (incidencias.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(incidencias);
        }
    }

    @GetMapping("/buscar/{id}") // ENDPOINT OK
    public ResponseEntity<SoporteIncidencia> obtenerPorId(@PathVariable int id) {
        try {
            SoporteIncidencia incidencia = incidenciaService.obtenerIncidenciaPorId(id);
            return ResponseEntity.ok(incidencia); // 200 OK con el objeto
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found si no existe
        }
    }


    // Actualizar una incidencia por su id
    @PutMapping("/update/{id}")  
    public ResponseEntity<SoporteIncidencia> actualizarIncidencia(
            @PathVariable int id,
            @RequestBody SoporteIncidencia nuevaIncidencia) {

        SoporteIncidencia actualizada = incidenciaService.actualizarIncidencia(id, nuevaIncidencia);
        return ResponseEntity.ok(actualizada);
    }


    // Obtener una incidencia por el id de un soporte     
    @GetMapping("/buscarPorSoporte/{id}") // ENDPOINT OK
    public ResponseEntity<List<SoporteIncidencia>> buscarPorSoporteId(@PathVariable Integer idSoporteSistema) {
        List<SoporteIncidencia> nuevoSoporte = incidenciaService.obtenerIncidenciaPorIdSoporteSistema(idSoporteSistema);
        return ResponseEntity.ok(nuevoSoporte);
    }
}
