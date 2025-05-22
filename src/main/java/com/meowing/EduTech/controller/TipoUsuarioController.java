package com.meowing.EduTech.controller;


import com.meowing.EduTech.model.TipoUsuario;
import com.meowing.EduTech.service.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tipousuario")
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;


    @PostMapping
    public ResponseEntity<TipoUsuario> crear(@RequestBody TipoUsuario tipoUsuario) {
        TipoUsuario nuevoTipoUsuario = tipoUsuarioService.guardarTipoUsuario(tipoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTipoUsuario);
    }


    @GetMapping
    public ResponseEntity<List<TipoUsuario>> listar() {
        List<TipoUsuario> tipoUsuarios = tipoUsuarioService.obtenerTipoUsuarios();
        if (tipoUsuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tipoUsuarios);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TipoUsuario> obtenerEvaluacion(@PathVariable int id) {
        try{
            TipoUsuario tipoUsuario = tipoUsuarioService.obtenerTipoUsuario(id);
            return ResponseEntity.ok(tipoUsuario);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

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