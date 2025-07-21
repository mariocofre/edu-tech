package com.meowing.EduTech.controller;


import com.meowing.EduTech.model.CursoContenido;
import com.meowing.EduTech.service.CursoContenidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cursocontenido")
public class CursoContenidoController {

    @Autowired
    private CursoContenidoService cursoContenidoService;



    @Operation(
        summary = "Crear nuevo contenido de curso",
        description = "Permite registrar un nuevo contenido relacionado a un curso."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Contenido creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PostMapping
    public ResponseEntity<CursoContenido> crear(@RequestBody CursoContenido cursoContenido) {
        CursoContenido nuevaCursoContenido = cursoContenidoService.guardarCursoContenido(cursoContenido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCursoContenido);
    }



    @Operation(
        summary = "Listar todos los contenidos de cursos",
        description = "Devuelve una lista con todos los contenidos registrados en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contenidos encontrados"),
        @ApiResponse(responseCode = "204", description = "No hay contenidos disponibles")
    })
    @GetMapping
    public ResponseEntity<List<CursoContenido>> listar() {
        List<CursoContenido> cursoContenidos = cursoContenidoService.obtenerCursoContenidos();
        if (cursoContenidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursoContenidos);
    }



    @Operation(
        summary = "Obtener contenido por ID",
        description = "Busca un contenido específico de curso por su identificador único."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contenido encontrado"),
        @ApiResponse(responseCode = "404", description = "Contenido no encontrado")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<CursoContenido> obtenerEvaluacion(@PathVariable int id) {
        try{
            CursoContenido cursoContenido = cursoContenidoService.obtenerCursoContenido(id);
            return ResponseEntity.ok(cursoContenido);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }




    @Operation(
        summary = "Eliminar contenido por ID",
        description = "Elimina un contenido específico asociado a un curso."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Contenido eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Contenido no encontrado")
    })
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        try {
            cursoContenidoService.eliminarCursoContenido(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    


    @Operation(
        summary = "Obtener contenidos por ID de curso",
        description = "Obtiene todos los contenidos asociados a un curso específico."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contenidos encontrados"),
        @ApiResponse(responseCode = "204", description = "No hay contenidos para ese curso")
    })
    @GetMapping("/curso/{idCurso}")
    public ResponseEntity<List<CursoContenido>> obtenerCursoContenido(@PathVariable Integer idCurso) {
        List<CursoContenido> comentarios = cursoContenidoService.obtenerCursoContenidoByCurso(idCurso);
        if (comentarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(comentarios);
        }
    }
}