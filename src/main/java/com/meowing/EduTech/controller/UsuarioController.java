package com.meowing.EduTech.controller;


import com.meowing.EduTech.model.Usuario;
import com.meowing.EduTech.service.UsuarioService;
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


    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }


    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/rut/{run}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable String run) {
        try{
            Usuario usuario = usuarioService.obtenerUsuario(run);
            return ResponseEntity.ok(usuario);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/rut/{run}")
    public ResponseEntity<Void> eliminar(@PathVariable String run) {
        try {
            usuarioService.eliminarUsuario(run);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //Obtener usuarios por tipo usuario
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