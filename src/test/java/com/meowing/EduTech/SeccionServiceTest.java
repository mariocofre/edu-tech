package com.meowing.EduTech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.meowing.EduTech.model.Curso;
import com.meowing.EduTech.model.Seccion;
import com.meowing.EduTech.model.TipoUsuario;
import com.meowing.EduTech.model.Usuario;
import com.meowing.EduTech.repository.SeccionRepository;
import com.meowing.EduTech.service.SeccionService;





@SpringBootTest
public class SeccionServiceTest {


    @Autowired
    private SeccionService seccionService;

    @MockBean
    private SeccionRepository seccionRepository;

    private TipoUsuario tipoUsuario;
    private Usuario usuario;
    private Curso curso;
    private Seccion seccion;


    @BeforeEach
    public void setUp() {
        curso = new Curso();
        curso.setIdCurso(1);
        curso.setNombre("Java Avanzado");
        curso.setFecha_inicio(new Date());
        curso.setFecha_termino(new Date());
        curso.setPrecio(25000.0);


        tipoUsuario = new TipoUsuario();
        tipoUsuario.setIdTipoUsuario(1);
        tipoUsuario.setTipoUsuario("Soporte");

        
        usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setRunUsuario("12345678-9");
        usuario.setNombreUsuario("Juan");
        usuario.setApellidoUsuario("Pérez");
        usuario.setPasswordUsuario("claveSegura123");
        usuario.setEmailUsuario("juan.perez@correo.com");
        usuario.setTipoUsuario(tipoUsuario);


        seccion = new Seccion();
        seccion.setIdSeccion(1);
        seccion.setCodigoSeccion("001D");
        seccion.setComentarios("Sección muy ordenada. el pepepepepepeppeeppeepepepepepeepepepepepe");
        seccion.setCurso(curso);
        seccion.setUsuario(usuario);
    }


    // Test para guardar una Seccion
    @Test
    public void testGuardarSeccion() {

        // Define el comportamiento del mock: cuando se llame a 
        // save(), devuelve la Seccion proporcionada
        when(seccionRepository.save(seccion)).thenReturn(seccion);

        // Llama al método save() del service
        Seccion saved = seccionService.guardarSeccion(seccion);

        // Verifica que la Seccion guardada no sea nula.
        // Verifica que su nombre coincida con el nombre esperado.
        // Verifica que su run coincida con el run esperado.
        assertNotNull(saved);
        assertEquals(1, saved.getIdSeccion());
        assertEquals("001D", saved.getCodigoSeccion());
    }


    // Test para obtener todas las Secciones
    @Test
    public void testObtenerSecciones() {

        // Cuando se llame al método findAll() del repository,
        // devuelve una lista que contiene el objeto seccion
        when(seccionRepository.findAll()).thenReturn(List.of(seccion));


        // Llama al método obtenerSecciones() del service y guarda
        // el resultado en la variable secciones
        List<Seccion> secciones = seccionService.obtenerSecciones();


        // Verifica que la lista secciones no sea null
        assertNotNull(secciones);
        // Verifica que la lista secciones tenga exactamente 1 elemento
        assertEquals(1, secciones.size());
    }



    // Test para obtener una seccion por su id
    @Test
    public void testObtenerSeccion() {
        int id = 1;

        // Cuando se llame al método findById(id) del repository,
        // devuelve un Optional que contiene el objeto seccion
        when(seccionRepository.findById(id)).thenReturn(Optional.of(seccion));


        // Llama al método obtenerSeccion(id) del serive 
        // para buscar una seccion por su id, y guarda el 
        // resultado en la variable found
        Seccion found = seccionService.obtenerSeccion(id);


        // Verifica que el objeto found no sea null 
        assertNotNull(found);
        // Verifica que el id de la seccion encontrada sea igual al id buscado
        assertEquals(id, found.getIdSeccion());
    }


    // Test para eliminar una seccion
    @Test
    public void testEliminarSeccion() {
        int id = 1;

        doNothing().when(seccionRepository).deleteById(id);

        seccionService.eliminarSeccion(id);
            
        verify(seccionRepository, times(1)).deleteById(id);
    }




    // Test para obtener una seccion por el id de curso relacionado
    @Test
    public void testObtenerSeccionByCurso() {
        // Id de curso a buscar 
        int idCurso = 1;

        // Simulamos que el repository retorna un alista con ese idCurso
        when(seccionRepository.findByCurso_IdCurso(idCurso)).thenReturn(List.of(seccion));

        List<Seccion> resultado = seccionService.obtenerSeccionByCurso(idCurso);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(resultado, resultado);
        assertEquals("001D", resultado.get(0).getCodigoSeccion());
        assertEquals(1, resultado.get(0).getIdSeccion());
    }



    // Test para obtener una seccion por el id de usuario relacionado
    @Test
    public void obtenerSeccionByUsuario() {

        int idUsuario = 1;

        when(seccionRepository.findByUsuario_IdUsuario(idUsuario)).thenReturn(List.of(seccion));

        List<Seccion> resultado = seccionService.obtenerSeccionByUsuario(idUsuario);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(resultado, resultado);
        assertEquals("001D", resultado.get(0).getCodigoSeccion());
        assertEquals(1, resultado.get(0).getIdSeccion());        
    }


}
