package com.meowing.EduTech;


import com.meowing.EduTech.model.*;
import com.meowing.EduTech.repository.NotaRepository;
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
public class NotaServiceTest {

    @Autowired
    private NotaService notaService;

    @MockBean
    private NotaRepository notaRepository;

    private Nota nota;
    private TipoUsuario tipoUsuario;
    private Usuario usuario;
    private Curso curso;
    private Seccion seccion;
    private Evaluacion evaluacion;

    @BeforeEach
    public void setUp() {

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

        // Creo Evaluacion
        evaluacion = new Evaluacion();
        evaluacion.setIdEvaluacion(1);
        evaluacion.setTema("Numeros y cosas lineales");
        evaluacion.setFechaEvaluacion(new Date());
        evaluacion.setSeccion(seccion);

        // Creo Nota
        nota = new Nota();

        nota.setIdNota(1);
        nota.setNota(7F);
        nota.setEvaluacion(evaluacion);
        nota.setUsuario(usuario);
    }

    @Test
    public void testObtenerNota(){
        when(notaRepository.findAll()).thenReturn(List.of(nota));

        List<Nota> notas = notaService.obtenerNotas();

        assertNotNull(notas);
        assertEquals(1,notas.size());
        assertEquals(nota,notas.get(0));
    }
    @Test
    public void testSubirNota(){
        Nota nota = new Nota(2,7F,evaluacion,usuario);

        when(notaRepository.save(nota)).thenReturn(nota);

        Nota savedNota = notaService.subirNota(nota);

        assertNotNull(savedNota);
        assertEquals(nota,savedNota);
    }

    @Test
    public void testObtenerNotaUsuario(){

        when(notaService.obtenerNotasPorUsuario(usuario.getIdUsuario())).thenReturn(List.of(nota));

        List<Nota> found = notaService.obtenerNotasPorUsuario(usuario.getIdUsuario());

        assertNotNull(found);
        assertEquals(1,found.size());
        assertEquals(nota,found.get(0));
        assertEquals(usuario.getIdUsuario(),found.get(0).getUsuario().getIdUsuario());
    }


}
