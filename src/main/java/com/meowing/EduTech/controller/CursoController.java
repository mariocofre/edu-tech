package com.meowing.EduTech.controller;



import com.meowing.EduTech.model.Curso;
import com.meowing.EduTech.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/curso")
public class CursoController {
    @Autowired
    private CursoService cursoService;


    @Operation(
        summary = "Crear un nuevo curso",
        description = "Permite registrar un nuevo curso en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Curso creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<Curso> crear(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoService.guardarCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCurso);
    }




    @Operation(
        summary = "Listar todos los cursos",
        description = "Devuelve una lista con todos los cursos registrados."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cursos encontrados"),
        @ApiResponse(responseCode = "204", description = "No hay cursos disponibles")
    })
    @GetMapping
    public ResponseEntity<List<Curso>> listar() {
        List<Curso> cursos = cursoService.obtenerCursos();
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }




    @Operation(
        summary = "Obtener curso por ID",
        description = "Devuelve la información de un curso específico según su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso encontrado"),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<Curso> obtenerCurso(@PathVariable int id) {
        try{
            Curso curso = cursoService.obtenerCurso(id);
            return ResponseEntity.ok(curso);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }



    @Operation(
        summary = "Eliminar curso por ID",
        description = "Elimina un curso específico según su identificador."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Curso eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        try {
            cursoService.eliminarCurso(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}

