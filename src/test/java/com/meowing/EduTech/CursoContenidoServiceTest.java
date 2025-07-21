package com.meowing.EduTech;

import com.meowing.EduTech.model.Curso;
import com.meowing.EduTech.model.CursoContenido;
import com.meowing.EduTech.repository.CursoContenidoRepository;
import com.meowing.EduTech.service.CursoContenidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CursoContenidoServiceTest {

    @Autowired
    private CursoContenidoService cursoContenidoService;

    @MockBean
    private CursoContenidoRepository cursoContenidoRepository;

    private Curso curso;
    private CursoContenido cursoContenido;

    @BeforeEach
    public void setUp() {
        curso = new Curso();
        curso.setIdCurso(1);
        curso.setNombre("Curso Spring Boot");
        curso.setFecha_inicio(new Date());
        curso.setFecha_termino(new Date());
        curso.setPrecio(10000.0);

        cursoContenido = new CursoContenido();
        cursoContenido.setIdCursoContenido(1);
        cursoContenido.setEncabezado("Introducción");
        cursoContenido.setContenido("Contenido de introducción al curso.");
        cursoContenido.setFechaActualizacion(new Date());
        cursoContenido.setCurso(curso);
    }

    @Test
    public void testObtenerCursoContenidos() {
        when(cursoContenidoRepository.findAll()).thenReturn(List.of(cursoContenido));

        List<CursoContenido> contenidos = cursoContenidoService.obtenerCursoContenidos();

        assertNotNull(contenidos);
        assertEquals(1, contenidos.size());
        assertEquals("Introducción", contenidos.get(0).getEncabezado());
    }

    @Test
    public void testObtenerCursoContenidoPorId() {
        when(cursoContenidoRepository.findById(1)).thenReturn(Optional.of(cursoContenido));

        CursoContenido resultado = cursoContenidoService.obtenerCursoContenido(1);

        assertNotNull(resultado);
        assertEquals("Introducción", resultado.getEncabezado());
    }

    @Test
    public void testGuardarCursoContenido() {
        when(cursoContenidoRepository.save(cursoContenido)).thenReturn(cursoContenido);

        CursoContenido guardado = cursoContenidoService.guardarCursoContenido(cursoContenido);

        assertNotNull(guardado);
        assertEquals("Introducción", guardado.getEncabezado());
        verify(cursoContenidoRepository, times(1)).save(cursoContenido);
    }

    @Test
    public void testEliminarCursoContenido() {
        doNothing().when(cursoContenidoRepository).deleteById(1);

        cursoContenidoService.eliminarCursoContenido(1);

        verify(cursoContenidoRepository, times(1)).deleteById(1);
    }

    @Test
    public void testObtenerCursoContenidoByCurso() {
        when(cursoContenidoRepository.findByCurso_IdCurso(1)).thenReturn(List.of(cursoContenido));

        List<CursoContenido> resultado = cursoContenidoService.obtenerCursoContenidoByCurso(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Curso Spring Boot", resultado.get(0).getCurso().getNombre());
    }
}