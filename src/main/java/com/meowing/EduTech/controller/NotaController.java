package com.Meowing.Evaluaciones.controller;

import com.Meowing.Evaluaciones.model.Nota;
import com.Meowing.Evaluaciones.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    // Listar Notas
    @GetMapping
    public ResponseEntity<List<Nota>> listarNotas() {
        List<Nota> notas = notaService.obtenerNotas();
        if (notas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(notas);
        }
    }

    @PostMapping
    public ResponseEntity<Nota> crearNota(@RequestBody Nota nota) {
        Nota nuevaNota = notaService.subirNota(nota);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaNota);
    }

    /* [Descomentar cuando Usuario se encuentre operativo]
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Nota>> obtenerNotaDeUsuario(@PathVariable Integer idUsuario) {
        List<Nota> nuevaNota = notaService.obtenerNotasPorUsuario(idUsuario);
        return ResponseEntity.ok(nuevaNota);
    }
    */
}
