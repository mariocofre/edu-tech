package com.meowing.EduTech.controller;


import com.meowing.EduTech.model.CursoContenido;
import com.meowing.EduTech.service.CursoContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cursocontenido")
public class CursoContenidoController {
    @Autowired
    private CursoContenidoService cursoContenidoService;


    @PostMapping
    public ResponseEntity<CursoContenido> crear(@RequestBody CursoContenido cursoContenido) {
        CursoContenido nuevaCursoContenido = cursoContenidoService.guardarCursoContenido(cursoContenido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCursoContenido);
    }


    @GetMapping
    public ResponseEntity<List<CursoContenido>> listar() {
        List<CursoContenido> cursoContenidos = cursoContenidoService.obtenerCursoContenidos();
        if (cursoContenidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursoContenidos);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CursoContenido> obtenerEvaluacion(@PathVariable int id) {
        try{
            CursoContenido cursoContenido = cursoContenidoService.obtenerCursoContenido(id);
            return ResponseEntity.ok(cursoContenido);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        try {
            cursoContenidoService.eliminarCursoContenido(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //Obtener comentarios por id de foro
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