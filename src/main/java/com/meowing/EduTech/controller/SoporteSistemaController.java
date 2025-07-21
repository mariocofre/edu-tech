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
import com.meowing.EduTech.model.SoporteSistema;
import com.meowing.EduTech.service.SoporteSistemaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;



@RestController
@RequestMapping("/api/v1/soportes")
//** CLASE DEL MICROSERVICIO 2.- MONITOREO DEL SISTEMA **
public class SoporteSistemaController {


    @Autowired
    private SoporteSistemaService soporteService;


    

    @Operation(
        summary = "Listar todos los soportes del sistema",
        description = "Obtiene todos los registros de soporte técnico registrados en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de soportes encontrada"),
        @ApiResponse(responseCode = "204", description = "No hay soportes registrados")
    })
    @GetMapping
    public ResponseEntity<List<SoporteSistema>> listarSoportes() {
        List<SoporteSistema> soportes = soporteService.obtenerSoportes();
        if (soportes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(soportes);
        }
    }


    

    @Operation(
        summary = "Crear un nuevo soporte",
        description = "Registra un nuevo soporte técnico en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Soporte creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<SoporteSistema> crearSoporte(@RequestBody SoporteSistema soporte) {
        SoporteSistema nuevoSoporte = soporteService.agregarSoporte(soporte);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSoporte);
    }


    

    @Operation(
        summary = "Obtener soporte por ID",
        description = "Devuelve un soporte técnico específico según su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Soporte encontrado"),
        @ApiResponse(responseCode = "404", description = "Soporte no encontrado")
    })
    @GetMapping("/buscar/{idSoporte}") 
    public ResponseEntity<SoporteSistema> listarPorIdSoporte(@PathVariable int idSoporte) {
        try {
            SoporteSistema soporte = soporteService.obtenerSoportePorId(idSoporte);
            return ResponseEntity.ok(soporte); 
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); 
        }
    }


    


    @Operation(
        summary = "Listar soportes por ID de usuario",
        description = "Obtiene todos los soportes asociados a un usuario específico según su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Soportes encontrados para el usuario"),
        @ApiResponse(responseCode = "404", description = "No se encontraron soportes para el usuario")
    })
    @GetMapping("/buscarPorUsuario/{idUsuario}")
    public ResponseEntity<List<SoporteSistema>> listarPorIdUsuario(@PathVariable Integer idUsuario) {
        List<SoporteSistema> nuevoSoporte = soporteService.obtenerSoportePorIdUsuario(idUsuario);
        return ResponseEntity.ok(nuevoSoporte);
    }
    
    
}
