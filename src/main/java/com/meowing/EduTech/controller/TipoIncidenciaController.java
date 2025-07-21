package com.meowing.EduTech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meowing.EduTech.model.TipoIncidencia;
import com.meowing.EduTech.service.TipoIncidenciaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


//** CLASE DEL MICROSERVICIO 2.- MONITOREO DEL SISTEMA **

@RestController
@RequestMapping("/api/v1/tipoIncidencia")
public class TipoIncidenciaController {


    @Autowired
    private TipoIncidenciaService tipoIncidenciaService;



    @Operation(
        summary = "Listar tipos de incidencias",
        description = "Obtiene todos los tipos de incidencias disponibles en el sistema de monitoreo."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de tipos de incidencia encontrada"),
        @ApiResponse(responseCode = "204", description = "No hay tipos de incidencia registrados")
    })
    @GetMapping
    public ResponseEntity<List<TipoIncidencia>> listarTipoIncidencias() {
        List<TipoIncidencia> tipoIncidencias = tipoIncidenciaService.obtenerTipoIncidencias();
        if (tipoIncidencias.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(tipoIncidencias);
        }
    }





    @Operation(
        summary = "Obtener tipo de incidencia por ID",
        description = "Devuelve un tipo de incidencia específico según su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipo de incidencia encontrado"),
        @ApiResponse(responseCode = "404", description = "Tipo de incidencia no encontrado")
    })
    @GetMapping("/tipoIncidencia{id}")
    public ResponseEntity<TipoIncidencia> listarTipoIncidenciaById(@PathVariable int id) {
        try {
            TipoIncidencia tipoIncidencia = tipoIncidenciaService.obtenerTipoIncidencia(id);
            return ResponseEntity.ok(tipoIncidencia);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    


    @Operation(
        summary = "Crear tipo de incidencia",
        description = "Registra un nuevo tipo de incidencia para el monitoreo del sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Tipo de incidencia creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<TipoIncidencia> creartipoIncidencia(@RequestBody TipoIncidencia tipo) {
        TipoIncidencia nuevoTipo = tipoIncidenciaService.agregarTipoIncidencia(tipo);   
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTipo);
    }

}
