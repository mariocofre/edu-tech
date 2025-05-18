package com.meowing.EduTech.controller;

import com.meowing.EduTech.model.Foro;
import com.meowing.EduTech.service.ForoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/foro")
public class ForoController {

    @Autowired
    private ForoService foroService;

    @PostMapping
    public ResponseEntity<Foro> createForo(@RequestBody Foro foro) {
        Foro nuevoForo = foroService.crearForo(foro);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoForo);
    }

    @GetMapping
    public ResponseEntity<List<Foro>> listForos() {
        List<Foro> foros = foroService.obtenerrForos();
        if (foros.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(foros);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Foro> deleteForo(@PathVariable Integer id) {
        try {
            foroService.eliminarForo(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Encontrar foro por idSeccion
    /*
    @GetMapping("/seccion/{id}")
    public ResponseEntity<List<Foro>> foroBySeccion(@PathVariable int id) {
        List<Foro> foros = foroService.findByidSeccion(idSeccion);
        return ResponseEntity.ok(foros);
    }
     */
}
