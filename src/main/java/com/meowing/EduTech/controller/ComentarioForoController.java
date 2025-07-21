    package com.meowing.EduTech.controller;

    import com.meowing.EduTech.model.ComentarioForo;
    import com.meowing.EduTech.service.ComentarioForoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/v1/comentarios")
    public class ComentarioForoController {

        @Autowired
        private ComentarioForoService comentarioForoService;



        @Operation(
            summary = "Crear un nuevo comentario en el foro",
            description = "Permite a un usuario comentar dentro de un foro específico."
        )
        @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comentario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
        })
        @PostMapping
        public ResponseEntity<ComentarioForo> comentar(@RequestBody ComentarioForo comentarioForo) {
            ComentarioForo comentario = comentarioForoService.crearComentario(comentarioForo);
            return ResponseEntity.status(HttpStatus.CREATED).body(comentario);
        }



        @Operation(
            summary = "Listar todos los comentarios del foro",
            description = "Retorna todos los comentarios registrados en el sistema."
        )
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentarios encontrados"),
            @ApiResponse(responseCode = "204", description = "No hay comentarios disponibles")
        })
        @GetMapping
        public ResponseEntity<List<ComentarioForo>> listarComentarios() {
            List<ComentarioForo> comentarios = comentarioForoService.getAll();
            if (comentarios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(comentarios);
            }
        }



        @Operation(
            summary = "Eliminar un comentario por ID",
            description = "Elimina un comentario específico del foro según su ID."
        )
        @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Comentario eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Comentario no encontrado")
        })
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Void> delete(@PathVariable Integer id) {
            try{
                comentarioForoService.eliminarComentario(id);
                return ResponseEntity.noContent().build();
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }

        


        @Operation(
            summary = "Obtener comentarios por ID de foro",
            description = "Devuelve todos los comentarios asociados a un foro específico."
        )
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentarios encontrados"),
            @ApiResponse(responseCode = "204", description = "No hay comentarios para ese foro")
        })
        @GetMapping("/foro/{idForo}")
        public ResponseEntity<List<ComentarioForo>> obtenerComentario(@PathVariable Integer idForo) {
            List<ComentarioForo> comentarios = comentarioForoService.obtenerComentarioByForo(idForo);
            if (comentarios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(comentarios);
            }
        }

        


        @Operation(
            summary = "Obtener comentarios por ID de usuario",
            description = "Devuelve todos los comentarios realizados por un usuario específico."
        )
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentarios encontrados"),
            @ApiResponse(responseCode = "204", description = "No hay comentarios de ese usuario")
        })
        @GetMapping("/usuario/{id}")
        public ResponseEntity<List<ComentarioForo>> obtenerComentarioByUsuario(@PathVariable Integer idUsuario) {
            List<ComentarioForo> comentarios = comentarioForoService.obtenerComentarioByUsuario(idUsuario);
            if (comentarios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(comentarios);
            }
        }
    }
