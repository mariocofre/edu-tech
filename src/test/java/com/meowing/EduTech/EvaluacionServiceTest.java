package com.meowing.EduTech;


import com.meowing.EduTech.model.*;
import com.meowing.EduTech.repository.EvaluacionRepository;
import com.meowing.EduTech.service.EvaluacionService;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EvaluacionServiceTest {

    @Autowired
    private EvaluacionService evaluacionService;

    @MockBean
    private EvaluacionRepository evaluacionRepository;


    private TipoUsuario tipoUsuario;
    private Usuario usuario;
    private Curso curso;
    private Seccion seccion;
    private Evaluacion evaluacion;

    @BeforeEach
    public void setup(){

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
        evaluacion.setTema("Desarrollo orientado a objetos");
        evaluacion.setFechaEvaluacion(new Date());
        evaluacion.setSeccion(seccion);
    }

    @Test
    public void testObtenerEvaluaciones(){
        when(evaluacionRepository.findAll()).thenReturn(List.of(evaluacion));

        List<Evaluacion> evaluaciones = evaluacionService.obtenerEvaluaciones();

        assertNotNull(evaluaciones);
        assertEquals(1, evaluaciones.size());
        assertEquals(evaluacion, evaluaciones.get(0));
    }

    @Test
    public void testObtenerEvaluacion(){
        when(evaluacionRepository.findById(1)).thenReturn(Optional.of(evaluacion));

        Evaluacion evaluacionEncontrada = evaluacionService.obtenerEvaluacion(1);

        assertNotNull(evaluacionEncontrada);
        assertEquals(evaluacion, evaluacionEncontrada);
    }

    @Test
    public void testGuardarEvaluacion(){
        Evaluacion evaluacion = new Evaluacion(2,"Lenguaje",new Date(),seccion);

        when(evaluacionRepository.save(evaluacion)).thenReturn(evaluacion);

        Evaluacion evaluacionEncontrada = evaluacionService.guardarEvaluacion(evaluacion);

        assertNotNull(evaluacionEncontrada);
        assertEquals(evaluacion, evaluacionEncontrada);
    }

    @Test
    public void testEliminarEvaluacion(){

        doNothing().when(evaluacionRepository).deleteById(1);

        evaluacionService.eliminarEvaluacion(1);

        verify(evaluacionRepository, times(1)).deleteById(1);
    }


}
