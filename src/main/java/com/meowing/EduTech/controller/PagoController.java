package com.meowing.EduTech.controller;

import com.meowing.EduTech.model.Pago;
import com.meowing.EduTech.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pago")
@Tag(name = "Pago", description = "Operaciones relacionadas con pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Operation(summary = "Listar todos los pagos", description = "Obtiene una lista de todos los pagos registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pagos encontrada"),
        @ApiResponse(responseCode = "204", description = "No hay pagos registrados")
    })
    @GetMapping
    public ResponseEntity<List<Pago>> listaPagosr() {
        List<Pago> pagos = pagoService.obtenerPagos();
        if (pagos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pagos);
    }

    @Operation(summary = "Crear un nuevo pago", description = "Registra un nuevo pago en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pago creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error en los datos enviados")
    })
    @PostMapping
    public ResponseEntity<Pago> crearPago(@RequestBody Pago pago) {
        Pago nuevoPago = pagoService.guardarPago(pago);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPago);
    }

    @Operation(summary = "Buscar un pago por ID", description = "Obtiene los datos de un pago según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago encontrado"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Pago> obtenerPago(@PathVariable int id) {
        try {
            Pago pago = pagoService.obtenerPago(id);
            return ResponseEntity.ok(pago);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

