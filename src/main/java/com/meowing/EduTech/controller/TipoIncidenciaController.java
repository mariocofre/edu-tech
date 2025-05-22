package com.meowing.EduTech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meowing.EduTech.model.TipoIncidencia;
import com.meowing.EduTech.service.TipoIncidenciaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



//** CLASE DEL MICROSERVICIO 2.- MONITOREO DEL SISTEMA **

@RestController
@RequestMapping("/api/v1/tipoIncidencia")
public class TipoIncidenciaController {

    private static final Logger logger = LoggerFactory.getLogger(TipoIncidenciaController.class);

    @Autowired
    private TipoIncidenciaService tipoIncidenciaService;

    // Listar tipos de incidencias
    @GetMapping
    public ResponseEntity<List<TipoIncidencia>> listarTipoIncidencias() {
        List<TipoIncidencia> tipoIncidencias = tipoIncidenciaService.obtenerTipoIncidencias();
        if (tipoIncidencias.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(tipoIncidencias);
        }
    }
    
    // Crear tipo de incidencia
    @PostMapping
    public ResponseEntity<?> creartipoIncidencia(@RequestBody TipoIncidencia tipo) {
        TipoIncidencia nuevoTipo = tipoIncidenciaService.agregarTipoIncidencia(tipo);   
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTipo);
    }
}
    


