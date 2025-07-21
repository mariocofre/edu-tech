package com.meowing.EduTech.controller;


import com.meowing.EduTech.model.Seccion;
import com.meowing.EduTech.service.SeccionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seccion")
public class SeccionController {
    @Autowired
    private SeccionService seccionService;




    @Operation(summary = "Crear una nueva sección", description = "Registra una nueva sección para un curso.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Sección creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<Seccion> crear(@RequestBody Seccion seccion) {
        Seccion nuevaSeccion = seccionService.guardarSeccion(seccion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSeccion);
    }




    @Operation(summary = "Listar todas las secciones", description = "Obtiene una lista de todas las secciones registradas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Secciones encontradas"),
        @ApiResponse(responseCode = "204", description = "No hay secciones registradas")
    })
    @GetMapping
    public ResponseEntity<List<Seccion>> listar() {
        List<Seccion> secciones = seccionService.obtenerSecciones();
        if (secciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(secciones);
    }




    @Operation(summary = "Obtener sección por ID", description = "Obtiene una sección específica según su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sección encontrada"),
        @ApiResponse(responseCode = "404", description = "Sección no encontrada")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<Seccion> obtenerSecciones(@PathVariable int id) {
        try{
            Seccion seccion = seccionService.obtenerSeccion(id);
            return ResponseEntity.ok(seccion);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }




    @Operation(summary = "Eliminar sección por ID", description = "Elimina una sección específica por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Sección eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Sección no encontrada")
    })
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        try {
            seccionService.eliminarSeccion(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    


    @Operation(summary = "Obtener secciones por ID de curso", description = "Obtiene todas las secciones asociadas a un curso específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Secciones encontradas"),
        @ApiResponse(responseCode = "204", description = "No hay secciones para el curso")
    })
    @GetMapping("/curso/{idCurso}")
    public ResponseEntity<List<Seccion>> obtenerSeccion(@PathVariable Integer idCurso) {
        List<Seccion> comentarios = seccionService.obtenerSeccionByCurso(idCurso);
        if (comentarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(comentarios);
        }
    }

    


    @Operation(summary = "Obtener secciones por ID de usuario", description = "Obtiene todas las secciones asociadas a un usuario específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Secciones encontradas"),
        @ApiResponse(responseCode = "204", description = "No hay secciones para el usuario")
    })
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Seccion>> obtenerSeccionByUsuario(@PathVariable Integer idUsuario) {
        List<Seccion> comentarios = seccionService.obtenerSeccionByUsuario(idUsuario);
        if (comentarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(comentarios);
        }
    }
}
