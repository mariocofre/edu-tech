package com.meowing.EduTech.controller;


import com.meowing.EduTech.model.Usuario;
import com.meowing.EduTech.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;



    @Operation(summary = "Crear un nuevo usuario", description = "Registra un nuevo usuario en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }




    @Operation(summary = "Listar todos los usuarios", description = "Obtiene una lista de todos los usuarios registrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuarios encontrada"),
        @ApiResponse(responseCode = "204", description = "No hay usuarios registrados")
    })
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }





    @Operation(summary = "Obtener usuario por RUT", description = "Busca un usuario específico por su RUT.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/rut/{run}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable String run) {
        try{
            Usuario usuario = usuarioService.obtenerUsuario(run);
            return ResponseEntity.ok(usuario);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }





    @Operation(summary = "Eliminar usuario por RUT", description = "Elimina un usuario existente identificado por su RUT.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/rut/{run}")
    public ResponseEntity<Void> eliminar(@PathVariable String run) {
        try {
            usuarioService.eliminarUsuario(run);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }





    @Operation(summary = "Obtener usuarios por tipo de usuario", description = "Obtiene todos los usuarios que pertenecen a un tipo de usuario específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuarios encontrados"),
        @ApiResponse(responseCode = "204", description = "No hay usuarios con ese tipo")
    })
    @GetMapping("/tipousuario/{idTipoUsuario}")
    public ResponseEntity<List<Usuario>> obtenerUsuario(@PathVariable Integer idTipoUsuario) {
        List<Usuario> comentarios = usuarioService.obtenerUsuarioByTipoUsuario(idTipoUsuario);
        if (comentarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(comentarios);
        }
    }
}