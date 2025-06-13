package com.meowing.EduTech;

import com.meowing.EduTech.model.Curso;
import com.meowing.EduTech.model.TipoUsuario;
import com.meowing.EduTech.model.Usuario;
import com.meowing.EduTech.repository.*;
import lombok.ToString;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Profile("test")
@Component
public class DataLoader implements CommandLineRunner {
    // Importamos los repositorios
    @Autowired
    private ComentarioForoRepository comentarioForoRepository;

    @Autowired
    private CursoContenidoRepository cursoContenidoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private ForoRepository foroRepository;

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private SeccionRepository seccionRepository;

    @Autowired
    private SoporteIncidenciaRepository soporteIncidenciaRepository;

    @Autowired
    private SoporteSistemaRepository soporteSistemaRepository;

    @Autowired
    private TipoIncidenciaRepository tipoIncidenciaRepository;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public void run(String... args)throws Exception{
        //Creo una instancia de faker y random
        Faker faker = new Faker();
        Random random = new Random();

        // Generar TipoUsuario
        //  Tipo Alumno
        TipoUsuario alumno = new TipoUsuario();
        alumno.setIdTipoUsuario(1);
        alumno.setTipoUsuario("Alumno");
        tipoUsuarioRepository.save(alumno);

        //  Tipo Profesor
        TipoUsuario profesor = new TipoUsuario();
        profesor.setIdTipoUsuario(2);
        profesor.setTipoUsuario("Profesor");
        tipoUsuarioRepository.save(profesor);

        //  Tipo Administrador
        TipoUsuario administrador = new TipoUsuario();
        administrador.setIdTipoUsuario(3);
        administrador.setTipoUsuario("Administrador");
        tipoUsuarioRepository.save(administrador);

        // Generar Curso

        for (int i = 0; i < 10; i++){
            Curso curso = new Curso();
            curso.setIdCurso(i+1);
            curso.setNombre(faker.educator().course());
            // No estoy convencido del uso de new Date para estos campos, lo hablare con el profe
            curso.setFecha_inicio(new Date());
            curso.setFecha_termino(new Date());
            // Formula del numero random:
            // min + (max - min) * random.nextFloat()
            curso.setPrecio(30000 + (100000 - 30000) * random.nextFloat());
            cursoRepository.save(curso);
        }

        // Generar Usuario

        //  Generar Alumnos
        for (int i = 0; i < 100; i++){
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(i+1);
            usuario.setNombreUsuario(faker.name().name());
            usuario.setApellidoUsuario(faker.name().lastName());
            usuario.setPasswordUsuario(faker.internet().password());
            usuario.setEmailUsuario(faker.internet().emailAddress());
            usuario.setTipoUsuario(alumno);
            usuarioRepository.save(usuario);
        }

        //  Generar profesores
        for (int i = 0; i < 10; i++){
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(i+1);
            usuario.setNombreUsuario(faker.name().name());
            usuario.setApellidoUsuario(faker.name().lastName());
            usuario.setPasswordUsuario(faker.internet().password());
            usuario.setEmailUsuario(faker.internet().emailAddress());
            usuario.setTipoUsuario(profesor);
            usuarioRepository.save(usuario);
        }

        //  Generar administradores
        for (int i = 0; i < 3; i++){
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(i+1);
            usuario.setNombreUsuario(faker.name().name());
            usuario.setApellidoUsuario(faker.name().lastName());
            usuario.setPasswordUsuario(faker.internet().password());
            usuario.setEmailUsuario(faker.internet().emailAddress());
            usuario.setTipoUsuario(administrador);
        }
    }


}
