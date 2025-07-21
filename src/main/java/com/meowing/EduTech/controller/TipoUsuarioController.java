package com.meowing.EduTech.controller;


import com.meowing.EduTech.model.TipoUsuario;
import com.meowing.EduTech.service.TipoUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipousuario")
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;




    @Operation(summary = "Crear un nuevo tipo de usuario", description = "Registra un nuevo tipo de usuario en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Tipo de usuario creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<TipoUsuario> crear(@RequestBody TipoUsuario tipoUsuario) {
        TipoUsuario nuevoTipoUsuario = tipoUsuarioService.guardarTipoUsuario(tipoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTipoUsuario);
    }




    @Operation(summary = "Listar todos los tipos de usuario", description = "Obtiene una lista de todos los tipos de usuario registrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de tipos de usuario encontrada"),
        @ApiResponse(responseCode = "204", description = "No hay tipos de usuario registrados")
    })
    @GetMapping
    public ResponseEntity<List<TipoUsuario>> listar() {
        List<TipoUsuario> tipoUsuarios = tipoUsuarioService.obtenerTipoUsuarios();
        if (tipoUsuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tipoUsuarios);
    }




    @Operation(summary = "Obtener tipo de usuario por ID", description = "Busca un tipo de usuario específico por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipo de usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Tipo de usuario no encontrado")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<TipoUsuario> obtenerEvaluacion(@PathVariable int id) {
        try{
            TipoUsuario tipoUsuario = tipoUsuarioService.obtenerTipoUsuario(id);
            return ResponseEntity.ok(tipoUsuario);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }




    @Operation(summary = "Eliminar tipo de usuario por ID", description = "Elimina un tipo de usuario existente por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Tipo de usuario eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Tipo de usuario no encontrado")
    })
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        try {
            tipoUsuarioService.eliminarTipoUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}