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

import com.meowing.EduTech.model.SoporteIncidencia;
import com.meowing.EduTech.model.SoporteSistema;
import com.meowing.EduTech.service.SoporteSistemaService;



@RestController
@RequestMapping("/api/v1/soportes")
//** CLASE DEL MICROSERVICIO 2.- MONITOREO DEL SISTEMA **
public class SoporteSistemaController {


    @Autowired
    private SoporteSistemaService soporteService;


    // Listar Soportes
    @GetMapping
    public ResponseEntity<List<SoporteSistema>> listarSoportes() {
        List<SoporteSistema> soportes = soporteService.obtenerSoportes();
        if (soportes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(soportes);
        }
    }


    // Crear un soporte
    @PostMapping
    public ResponseEntity<SoporteSistema> crearSoporte(@RequestBody SoporteSistema soporte) {
        SoporteSistema nuevoSoporte = soporteService.agregarSoporte(soporte);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSoporte);
    }


    // Listar un soporte por id soporte
    @GetMapping("/buscar/{idSoporte}") // ENDPOINT OK
    public ResponseEntity<SoporteSistema> listarPorIdSoporte(@PathVariable int idSoporte) {
        try {
            SoporteSistema soporte = soporteService.obtenerSoportePorId(idSoporte);
            return ResponseEntity.ok(soporte); // 200 OK con el objeto
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found si no existe
        }
    }


    // Listar un soporte por id de un usuario
    @GetMapping("/buscarPorUsuario/{idUsuario}")
    public ResponseEntity<List<SoporteSistema>> listarPorIdUsuario(@PathVariable Integer idUsuario) {
        List<SoporteSistema> nuevoSoporte = soporteService.obtenerSoportePorIdUsuario(idUsuario);
        return ResponseEntity.ok(nuevoSoporte);
    }
    
    
}
