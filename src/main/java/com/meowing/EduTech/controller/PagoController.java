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

import com.meowing.EduTech.model.Curso;
import com.meowing.EduTech.model.Pago;
import com.meowing.EduTech.service.PagoService;

@RestController
@RequestMapping("api/v1/pago")
public class PagoController {

    @Autowired
    private PagoService pagoService;


    @GetMapping
    public ResponseEntity<List<Pago>> listaPagosr() {
        List<Pago> pagos = pagoService.obtenerPagos();
        if (pagos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pagos);
    }

    @PostMapping
    public ResponseEntity<Pago> crearPago(@RequestBody Pago pago) {
        Pago nuevoPago = pagoService.guardarPago(pago);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPago);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Pago> obtenerPago(@PathVariable int id) {
        try{
            Pago pago= pagoService.obtenerPago(id);
            return ResponseEntity.ok(pago);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
