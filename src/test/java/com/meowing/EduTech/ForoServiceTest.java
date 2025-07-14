package com.meowing.EduTech;


import com.meowing.EduTech.model.*;
import com.meowing.EduTech.repository.ForoRepository;
import com.meowing.EduTech.service.ForoService;
import com.meowing.EduTech.service.NotaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ForoServiceTest {
    @Autowired
    private ForoService foroService;

    @MockBean
    private ForoRepository foroRepository;

    private TipoUsuario tipoUsuario;
    private Usuario usuario;
    private Curso curso;
    private Seccion seccion;
    private Foro foro;

    @BeforeEach
    public void setUp(){
        // Creo un Tipo usuario
        tipoUsuario = new TipoUsuario();
        tipoUsuario.setIdTipoUsuario(1);
        tipoUsuario.setTipoUsuario("Alumno");

        // Creo Usuario
        usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setRunUsuario("12345678-9");
        usuario.setNombreUsuario("Juan");
        usuario.setApellidoUsuario("Pérez");
        usuario.setPasswordUsuario("claveSegura123");
        usuario.setEmailUsuario("juan.perez@correo.com");
        usuario.setTipoUsuario(tipoUsuario);

        // Creo Curso
        curso = new Curso();
        curso.setIdCurso(1);
        curso.setNombre("AlgebraLineal");
        curso.setFecha_inicio(new Date());
        curso.setFecha_termino(new Date());
        curso.setPrecio(600.0);

        // Creo Seccion
        seccion = new Seccion();
        seccion.setIdSeccion(1);
        seccion.setCodigoSeccion("AL001B");
        seccion.setComentarios("Baby, baby, baby\n" +
                "Yeah!\n" +
                "Fear's awake, anger beats loud, face reality\n" +
                "Never beat charity\n" +
                "The enemy you're fighting covers all society\n" +
                "(Damn right)\n" +
                "Mommy's not here, gotta fight\n" +
                "(All night)\n" +
                "Right here, Shadow 10 o'clock direction\n" +
                "Seize the moment, destroy the nation\n" +
                "Your rhyme is slow motion, give me motivation\n" +
                "Freaked out now, and dead on arrival\n" +
                "(What?)\n" +
                "Round up around, spit out\n" +
                "All over\n" +
                "Rhyme like a rolling stone\n" +
                "Comin' a crowd\n" +
                "Watch out, they move, they diss you loud\n" +
                "Guess what this sound, it bombs whole ground\n" +
                "(So round up)\n" +
                "Don't ease your pace, 'cause enemy's brutal\n" +
                "Moment of truth, there ain't no truce\n" +
                "You're the only one, one world, one love\n" +
                "But the battle goes on, Shadows of Mass Destruction\n" +
                "\n");
        seccion.setCurso(curso);
        seccion.setUsuario(usuario);

        // Creo Foro
        foro = new Foro();
        foro.setIdforo(1);
        foro.setSeccion(seccion);
    }

    @Test
    public void testObtenerForos(){
        when(foroRepository.findAll()).thenReturn(List.of(foro));

        List<Foro> foros = foroService.obtenerrForos();

        assertNotNull(foros);
        assertEquals(foros.size(), 1);
        assertEquals(foro, foros.get(0));
    }

    @Test
    public void testCreaForo(){
        Foro foro = new Foro(2,seccion);

        when(foroRepository.save(foro)).thenReturn(foro);

        Foro savedForo = foroService.crearForo(foro);

        assertNotNull(savedForo);
        assertEquals(foro, savedForo);
    }

    @Test
    public void testEliminaForo(){
        doNothing().when(foroRepository).deleteById(1);

        foroService.eliminarForo(1);

        verify(foroRepository, times(1)).deleteById(1);
    }

    @Test
    public void testForosBySeccion(){
        when(foroRepository.findBySeccion_idSeccion(seccion.getIdSeccion())).thenReturn(List.of(foro));
        List<Foro> foros = foroService.forosBySeccion(seccion.getIdSeccion());

        assertNotNull(foros);
        assertEquals(foros.size(), 1);
        assertEquals(foro, foros.get(0));
        assertEquals(seccion, foros.get(0).getSeccion());
    }
}
