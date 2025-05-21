package com.meowing.EduTech.controller;


import com.meowing.EduTech.model.Seccion;
import com.meowing.EduTech.service.SeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/seccion")
public class SeccionController {
    @Autowired
    private SeccionService seccionService;


    @PostMapping
    public ResponseEntity<Seccion> crear(@RequestBody Seccion seccion) {
        Seccion nuevaSeccion = seccionService.guardarSeccion(seccion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSeccion);
    }


    @GetMapping
    public ResponseEntity<List<Seccion>> listar() {
        List<Seccion> secciones = seccionService.obtenerSecciones();
        if (secciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(secciones);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Seccion> obtenerSecciones(@PathVariable int id) {
        try{
            Seccion seccion = seccionService.obtenerSeccion(id);
            return ResponseEntity.ok(seccion);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        try {
            seccionService.eliminarSeccion(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //Obtener comentarios por id de foro
    @GetMapping("/curso/{idCurso}")
    public ResponseEntity<List<Seccion>> obtenerSeccion(@PathVariable Integer idCurso) {
        List<Seccion> comentarios = seccionService.obtenerSeccionByCurso(idCurso);
        if (comentarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(comentarios);
        }
    }

    //Obtener comentarios por id de foro
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Seccion>> obtenerSeccionByUsuario(@PathVariable Integer idUsuario) {
        List<Seccion> comentarios = seccionService.obtenerSeccionByUsuario(idUsuario);
        if (comentarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(comentarios);
        }
    }
}
