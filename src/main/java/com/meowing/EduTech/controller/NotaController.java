package com.meowing.EduTech.controller;


import com.meowing.EduTech.model.Nota;
import com.meowing.EduTech.service.NotaService;

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
@RequestMapping("/api/v1/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    

    @Operation(
        summary = "Listar todas las notas",
        description = "Obtiene todas las calificaciones registradas en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de notas encontrada"),
        @ApiResponse(responseCode = "204", description = "No hay notas registradas")
    })
    @GetMapping
    public ResponseEntity<List<Nota>> listarNotas() {
        List<Nota> notas = notaService.obtenerNotas();
        if (notas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(notas);
        }
    }




    @Operation(
        summary = "Crear una nueva nota",
        description = "Registra una nueva calificación en el sistema para un usuario en una evaluación."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Nota creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<Nota> crearNota(@RequestBody Nota nota) {
        Nota nuevaNota = notaService.subirNota(nota);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaNota);
    }



    @Operation(
        summary = "Obtener notas por ID de usuario",
        description = "Devuelve todas las notas asociadas a un usuario específico."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Notas del usuario encontradas"),
        @ApiResponse(responseCode = "204", description = "No hay notas para el usuario")
    })
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Nota>> obtenerNotaDeUsuario(@PathVariable Integer idUsuario) {
        List<Nota> nuevaNota = notaService.obtenerNotasPorUsuario(idUsuario);
        return ResponseEntity.ok(nuevaNota);
    }
}
