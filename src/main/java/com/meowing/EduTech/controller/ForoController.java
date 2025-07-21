package com.meowing.EduTech.controller;

import com.meowing.EduTech.model.Foro;
import com.meowing.EduTech.service.ForoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/foro")
public class ForoController {

    @Autowired
    private ForoService foroService;



    @Operation(
        summary = "Crear un nuevo foro",
        description = "Crea un foro nuevo y lo guarda en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Foro creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<Foro> createForo(@RequestBody Foro foro) {
        Foro nuevoForo = foroService.crearForo(foro);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoForo);
    }




    @Operation(
        summary = "Listar todos los foros",
        description = "Obtiene una lista con todos los foros creados en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de foros encontrada"),
        @ApiResponse(responseCode = "204", description = "No hay foros disponibles")
    })
    @GetMapping
    public ResponseEntity<List<Foro>> listForos() {
        List<Foro> foros = foroService.obtenerrForos();
        if (foros.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(foros);
        }
    }




    @Operation(
        summary = "Eliminar foro por ID",
        description = "Elimina un foro específico según su identificador."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Foro eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Foro no encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Foro> deleteForo(@PathVariable Integer id) {
        try {
            foroService.eliminarForo(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    


    @Operation(
        summary = "Obtener foros por ID de sección",
        description = "Devuelve una lista de foros asociados a una sección específica."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Foros encontrados para la sección"),
        @ApiResponse(responseCode = "204", description = "No hay foros asociados a la sección")
    })
    @GetMapping("/seccion/{id}")
    public ResponseEntity<List<Foro>> foroBySeccion(@PathVariable Integer id_Seccion) {
        List<Foro> foros = foroService.forosBySeccion(id_Seccion);
        return ResponseEntity.ok(foros);
    }
}
