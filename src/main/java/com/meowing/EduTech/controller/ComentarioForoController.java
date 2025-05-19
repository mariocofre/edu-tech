package com.meowing.EduTech.controller;

import com.meowing.EduTech.model.ComentarioForo;
import com.meowing.EduTech.service.ComentarioForoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/comentarios")
public class ComentarioForoController {

    @Autowired
    private ComentarioForoService comentarioForoService;

    @PostMapping
    public ResponseEntity comentar(@RequestBody ComentarioForo comentarioForo) {
        ComentarioForo comentario = comentarioForoService.crearComentario(comentarioForo);
        return ResponseEntity.status(HttpStatus.CREATED).body(comentario);
    }

    @GetMapping
    public ResponseEntity<List<ComentarioForo>> listarComentarios() {
        List<ComentarioForo> comentarios = comentarioForoService.getAll();
        if (comentarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(comentarios);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try{
            comentarioForoService.eliminarComentario(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Obtener comentarios por id de foro
    @GetMapping("/foro/{idForo}")
    public ResponseEntity<List<ComentarioForo>> obtenerComentario(@PathVariable Integer idForo) {
        List<ComentarioForo> comentarios = comentarioForoService.obtenerComentarioByForo(idForo);
        if (comentarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(comentarios);
        }
    }

    //Obtener comentarios por id de usuario
    /*
    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<ComentarioForo>> obtenerComentarioByUsuario(@PathVariable Integer idUsuario) {
        List<ComentarioForo> comentarios = comentarioForoService.findByUsuario(Integer idUsuario);
        if (comentarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(comentarios);
        }
    }
    */

}
