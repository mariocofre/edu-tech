package com.meowing.EduTech;

import com.meowing.EduTech.model.TipoUsuario;
import com.meowing.EduTech.model.Usuario;
import com.meowing.EduTech.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Random;

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



    }


}
