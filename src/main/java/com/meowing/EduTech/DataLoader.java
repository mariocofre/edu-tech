package com.meowing.EduTech;

import com.meowing.EduTech.model.*;
import com.meowing.EduTech.repository.*;
import lombok.ToString;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Profile("test?")
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
        //alumno.setIdTipoUsuario(1);
        alumno.setTipoUsuario("Alumno");
        tipoUsuarioRepository.save(alumno);

        //  Tipo Profesor
        TipoUsuario profesor = new TipoUsuario();
        //profesor.setIdTipoUsuario(2);
        profesor.setTipoUsuario("Profesor");
        tipoUsuarioRepository.save(profesor);

        //  Tipo Administrador
        TipoUsuario administrador = new TipoUsuario();
        //administrador.setIdTipoUsuario(3);
        administrador.setTipoUsuario("Administrador");
        tipoUsuarioRepository.save(administrador);

        // Generar Curso

        for (int i = 0; i < 10; i++){
            Curso curso = new Curso();
            //curso.setIdCurso(i+1);
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
            //usuario.setIdUsuario(i+1);
            usuario.setNombreUsuario(faker.name().name());
            usuario.setRunUsuario(faker.number().digits(8) + "-" + faker.lorem().characters(1).toUpperCase());
            usuario.setApellidoUsuario(faker.name().lastName());
            usuario.setPasswordUsuario(faker.internet().password());
            usuario.setEmailUsuario(faker.internet().emailAddress());
            usuario.setTipoUsuario(alumno);
            usuarioRepository.save(usuario);
        }

        //  Generar profesores
        for (int i = 0; i < 10; i++){
            Usuario usuario = new Usuario();
            //usuario.setIdUsuario(i+1);
            usuario.setNombreUsuario(faker.name().name());
            usuario.setRunUsuario(faker.number().digits(8) + "-" + faker.lorem().characters(1).toUpperCase());
            usuario.setApellidoUsuario(faker.name().lastName());
            usuario.setPasswordUsuario(faker.internet().password());
            usuario.setEmailUsuario(faker.internet().emailAddress());
            usuario.setTipoUsuario(profesor);
            usuarioRepository.save(usuario);
        }

        //  Generar administradores
        for (int i = 0; i < 3; i++){
            Usuario usuario = new Usuario();
            //usuario.setIdUsuario(i+1);
            usuario.setNombreUsuario(faker.name().name());
            usuario.setRunUsuario(faker.number().digits(8) + "-" + faker.lorem().characters(1).toUpperCase());
            usuario.setApellidoUsuario(faker.name().lastName());
            usuario.setPasswordUsuario(faker.internet().password());
            usuario.setEmailUsuario(faker.internet().emailAddress());
            usuario.setTipoUsuario(administrador);
            usuarioRepository.save(usuario);
        }

        // Obtener todos los Alumnos
        List<Usuario> usuarios = usuarioRepository.findByTipoUsuario_IdTipoUsuario(1);

        // Generar todos los cursos
        List<Curso> cursos = cursoRepository.findAll();

        // Generar Pago
        for (int i = 0; i < 100; i++){
            Pago pago = new Pago();
            //pago.setIdPago(i+1);
            pago.setComprobante(faker.code().asin());
            pago.setFecha(LocalDateTime.now());
            pago.setUsuario(usuarios.get(i));
            // El metodo con el cual se escoje un curso para el pago esta incompleto
            // Cuando termine el DataLoader revisare que tan necesario es relacionarlos correctamente
            pago.setCurso(cursos.get(random.nextInt(cursos.size())));
            pagoRepository.save(pago);
        }

        // Generar CursoContenido
        for (int i = 0; i < 30; i++){
            CursoContenido curso = new CursoContenido();
            //curso.setIdCursoContenido(i+1);
            curso.setEncabezado(faker.lorem().sentence(3));
            curso.setContenido(faker.lorem().sentence(70));
            curso.setFechaActualizacion(new Date());
            curso.setCurso(cursos.get(random.nextInt(cursos.size())));
            cursoContenidoRepository.save(curso);
        }

        // Genero datos para armar un codigo de seccion aleatorio
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        // Generar Seccion
        for (int i = 0; i < usuarios.size(); i++){
            Seccion seccion = new Seccion();
            //seccion.setIdSeccion(i+1);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 5; j++){
                sb.append(characters.charAt(random.nextInt(characters.length())));
            }
            seccion.setCodigoSeccion(sb.toString());
            seccion.setComentarios(faker.lorem().sentence(50));
            seccion.setCurso(cursos.get(random.nextInt(cursos.size())));
            seccion.setUsuario(usuarios.get(i));
            seccionRepository.save(seccion);
        }

        // Generar TipoIncidencia
        for (int i = 0; i < 5; i++){
            TipoIncidencia tipoIncidencia = new TipoIncidencia();
            //tipoIncidencia.setIdTipoIncidencia(i+1);
            tipoIncidencia.setTipo(faker.hacker().verb());
            tipoIncidenciaRepository.save(tipoIncidencia);
        }

        // Generar lista de usuarios administradores
        List<Usuario> admins = usuarioRepository.findByTipoUsuario_IdTipoUsuario(3);

        // Generar SoporteSistema
        for (int i = 0; i < 5; i++){
            SoporteSistema soporteSistema = new SoporteSistema();
            //soporteSistema.setIdSoporteSistema(i+1);
            soporteSistema.setUsuario(admins.get(random.nextInt(admins.size())));
            soporteSistemaRepository.save(soporteSistema);
        }

        // Listar todas las secciones
        List<Seccion> secciones = seccionRepository.findAll();

        // Generar Evaluacion
        // Para estas pruebas, ceraré una evaluacion por alumno y los conectare por los id que coincidan
        // por esto el ciclo for solo se ejecuta en funcion a los alumnos que hayan
        for (int i = 0; i < usuarios.size(); i++){
            Evaluacion evaluacion = new Evaluacion();
            //evaluacion.setIdEvaluacion(i+1);
            evaluacion.setTema(faker.educator().secondarySchool());
            evaluacion.setFechaEvaluacion(new Date());
            // aqui reviso las secciones hasta encontrar una que haga match con el usuario al que estamos
            // buscando, ahí recien agrego la seccion correcta a la evaluación
            for (int j = 0; j < secciones.size(); j++){
                if(secciones.get(j).getUsuario().getIdUsuario().equals(usuarios.get(i).getIdUsuario())){
                    evaluacion.setSeccion(secciones.get(j));
                    break;
                }
            }
            evaluacionRepository.save(evaluacion);
        }

        // Generar foro (haré un foro por seccion)
        for (int i = 0; i < secciones.size(); i++){
            Foro foro = new Foro();
            //foro.setIdforo(i+1);
            foro.setSeccion(secciones.get(i));
            foroRepository.save(foro);
        }

        //Listar SoporteSistemas
        List<SoporteSistema> sistemas = soporteSistemaRepository.findAll();

        //Listar TiposIncidencia
        List<TipoIncidencia> tipoIncidencias = tipoIncidenciaRepository.findAll();

        // Generar SoporteIncidencia
        for (int i = 0; i < sistemas.size(); i++){
            SoporteIncidencia soporteIncidencia = new SoporteIncidencia();
            //soporteIncidencia.setIdSoporteIncidencia(i+1);
            soporteIncidencia.setDetalles(faker.lorem().sentence(50));
            soporteIncidencia.setFechaInicioIncidencia(LocalDateTime.now());
            soporteIncidencia.setFechaTerminoIncidencia(LocalDateTime.now());
            soporteIncidencia.setSoporteSistema(sistemas.get(i));
            soporteIncidencia.setTipoIncidencia(tipoIncidencias.get(random.nextInt(tipoIncidencias.size())));
            soporteIncidenciaRepository.save(soporteIncidencia);
        }

        //Listar Foros
        List<Foro> foros = foroRepository.findAll();

        // Generar ComentarioForo
        for (int i = 0; i < 30; i++){
            ComentarioForo comentarioForo = new ComentarioForo();
            //comentarioForo.setId_comentario_foro(i+1);
            comentarioForo.setEncabezado(faker.lorem().sentence(5));
            comentarioForo.setMensaje(faker.lorem().sentence(50));
            comentarioForo.setFecha_publicacion(new Date());
            comentarioForo.setForo(foros.get(random.nextInt(foros.size())));
            comentarioForo.setUsuario(usuarios.get(random.nextInt(usuarios.size())));
            comentarioForoRepository.save(comentarioForo);
        }

        //Listar Evaluaciones
        List<Evaluacion> evaluaciones = evaluacionRepository.findAll();

        //Generar Nota
        for (int i = 0; i < usuarios.size(); i++){
            Nota nota = new Nota();
            //nota.setIdNota(i+1);
            nota.setNota(random.nextFloat() * 6.0f + 1.0f);
            nota.setEvaluacion(evaluaciones.get(i));
            nota.setUsuario(usuarios.get(i));
            notaRepository.save(nota);
        }
    }

}
