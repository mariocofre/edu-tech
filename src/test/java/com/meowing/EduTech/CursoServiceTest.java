package com.meowing.EduTech;

import com.meowing.EduTech.model.Curso;
import com.meowing.EduTech.repository.CursoRepository;
import com.meowing.EduTech.service.CursoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CursoServiceTest {

    @Autowired
    private CursoService cursoService;

    @MockBean
    private CursoRepository cursoRepository;

    private Curso curso;

    @BeforeEach
    public void setUp() {
        curso = new Curso();
        curso.setIdCurso(1);
        curso.setNombre("Java Avanzado");
        curso.setFecha_inicio(new Date());
        curso.setFecha_termino(new Date());
        curso.setPrecio(25000.0);
    }

    @Test
    public void testObtenerCursos() {
        when(cursoRepository.findAll()).thenReturn(List.of(curso));

        List<Curso> cursos = cursoService.obtenerCursos();

        assertNotNull(cursos);
        assertEquals(1, cursos.size());
        assertEquals("Java Avanzado", cursos.get(0).getNombre());
    }

    @Test
    public void testObtenerCurso() {
        when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));

        Curso resultado = cursoService.obtenerCurso(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdCurso());
        assertEquals("Java Avanzado", resultado.getNombre());
    }

    @Test
    public void testGuardarCurso() {
        when(cursoRepository.save(curso)).thenReturn(curso);

        Curso guardado = cursoService.guardarCurso(curso);

        assertNotNull(guardado);
        assertEquals("Java Avanzado", guardado.getNombre());
        verify(cursoRepository, times(1)).save(curso);
    }

    @Test
    public void testEliminarCurso() {
        doNothing().when(cursoRepository).deleteById(1);

        cursoService.eliminarCurso(1);

        verify(cursoRepository, times(1)).deleteById(1);
    }
}