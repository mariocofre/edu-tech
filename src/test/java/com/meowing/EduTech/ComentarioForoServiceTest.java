package com.meowing.EduTech;

import com.meowing.EduTech.model.ComentarioForo;
import com.meowing.EduTech.model.Foro;
import com.meowing.EduTech.model.Usuario;
import com.meowing.EduTech.repository.ComentarioForoRepository;
import com.meowing.EduTech.service.ComentarioForoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ComentarioForoServiceTest {

    @Autowired
    private ComentarioForoService comentarioForoService;

    @MockBean
    private ComentarioForoRepository comentarioForoRepository;

    private ComentarioForo comentario;
    private Foro foro;
    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        foro = new Foro();
        foro.setIdforo(1);

        usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setRunUsuario("12345678-9");
        usuario.setNombreUsuario("Pepito");
        usuario.setApellidoUsuario("Perez");
        usuario.setEmailUsuario("Pepito@correo.com");
        usuario.setPasswordUsuario("clave123");

        comentario = new ComentarioForo();
        comentario.setId_comentario_foro(1);
        comentario.setEncabezado("En mi opinion...");
        comentario.setMensaje("Me encanta");
        comentario.setFecha_publicacion(new Date());
        comentario.setForo(foro);
        comentario.setUsuario(usuario);
    }

    @Test
    public void testGetAll() {
        when(comentarioForoRepository.findAll()).thenReturn(List.of(comentario));

        List<ComentarioForo> resultado = comentarioForoService.getAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("En mi opinion...", resultado.get(0).getEncabezado());
    }

    @Test
    public void testObtenerComentarioPorId() {
        when(comentarioForoRepository.findById(1)).thenReturn(Optional.of(comentario));

        ComentarioForo resultado = comentarioForoService.obtenerComentario(1);

        assertNotNull(resultado);
        assertEquals("Me encanta", resultado.getMensaje());
    }

    @Test
    public void testCrearComentario() {
        when(comentarioForoRepository.save(comentario)).thenReturn(comentario);

        ComentarioForo guardado = comentarioForoService.crearComentario(comentario);

        assertNotNull(guardado);
        assertEquals("En mi opinion...", guardado.getEncabezado());
        verify(comentarioForoRepository, times(1)).save(comentario);
    }

    @Test
    public void testEliminarComentario() {
        doNothing().when(comentarioForoRepository).deleteById(1);

        comentarioForoService.eliminarComentario(1);

        verify(comentarioForoRepository, times(1)).deleteById(1);
    }

    @Test
    public void testObtenerComentarioByForo() {
        when(comentarioForoRepository.findByForo_Idforo(1)).thenReturn(List.of(comentario));

        List<ComentarioForo> resultado = comentarioForoService.obtenerComentarioByForo(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1, resultado.get(0).getForo().getIdforo());
    }

    @Test
    public void testObtenerComentarioByUsuario() {
        when(comentarioForoRepository.findByUsuario_idUsuario(1)).thenReturn(List.of(comentario));

        List<ComentarioForo> resultado = comentarioForoService.obtenerComentarioByUsuario(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Pepito", resultado.get(0).getUsuario().getNombreUsuario());
    }
}
