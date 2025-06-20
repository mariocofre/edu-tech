package com.meowing.EduTech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.meowing.EduTech.model.SoporteIncidencia;
import com.meowing.EduTech.model.SoporteSistema;
import com.meowing.EduTech.model.TipoIncidencia;
import com.meowing.EduTech.model.TipoUsuario;
import com.meowing.EduTech.model.Usuario;
import com.meowing.EduTech.repository.SoporteIncidenciaRepository;
import com.meowing.EduTech.service.SoporteIncidenciaService;




@SpringBootTest
public class SoporteIncidenciaServiceTest {

    // Inyecta el servicio de SoporteIncidencia para ser probado.
    @Autowired
    private SoporteIncidenciaService soporteIncidenciaService;


    // Crea un mock del repository de SoporteIncidencia para simular su comportamiento.
    @MockBean
    private SoporteIncidenciaRepository soporteIncidenciaRepository;

    
    private TipoUsuario tipoUsuario;
    private Usuario usuario;
    private SoporteSistema soporte;
    private TipoIncidencia tipoIncidencia;
    private SoporteIncidencia soporteIncidencia;


    // BeforeEach sirve para instanciar los objetos y no volver a escribirlos en cada test.
    @BeforeEach
    public void setUp() {
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

        soporte = new SoporteSistema();
        soporte.setIdSoporteSistema(1);
        soporte.setUsuario(usuario);

        tipoIncidencia = new TipoIncidencia();
        tipoIncidencia.setIdTipoIncidencia(1);
        tipoIncidencia.setTipo("Errores en la Base de Datos");

        soporteIncidencia = new SoporteIncidencia();
        soporteIncidencia.setIdSoporteIncidencia(1);
        soporteIncidencia.setDetalles("Problema resuelto, el proximo mes hacer un analisis profundo en las tablas de la Data Base");
        soporteIncidencia.setFechaInicioIncidencia(null);
        soporteIncidencia.setFechaTerminoIncidencia(null);
        soporteIncidencia.setSoporteSistema(soporte);
        soporteIncidencia.setTipoIncidencia(tipoIncidencia);

    }

    // Test para obtener todas las incidencias
    @Test
    public void testObtenerIncidencias() {

        // Define el comportamiento del mock: Cuando se llame a obtenerIncidencias(), devuelve una lista con un SoporteIncidencia.
        when(soporteIncidenciaRepository.findAll()).thenReturn(List.of(soporteIncidencia));

        // Llama al método obtenerIncidencias() del servicio
        List<SoporteIncidencia> incidencias = soporteIncidenciaService.obtenerIncidencias();

        // Verifica que la lista devuelta no sea nula y contenga exactamente un SoporteIncidencia.
        assertNotNull(incidencias);
        assertEquals(1, incidencias.size());
    }


    // Test para obtener una sola incidencia por su id.
    @Test
    public void testObtenerIncidenciaPorId() {
        int id = 1;
        
        // Define el comportamiento del mock: cuando se llame a findById() con "1", devuelve una Carrera opcional.
        when(soporteIncidenciaRepository.findById(id)).thenReturn(Optional.of(soporteIncidencia));

        // Llama al método obtenerIncidenciaPorId() del servicio.
        SoporteIncidencia found = soporteIncidenciaService.obtenerIncidenciaPorId(id);

        // Verifica que el SoporteIncidencia devuelta no sea nula y que su código coincida con el código esperado.
        assertNotNull(found);
        assertEquals(id, found.getIdSoporteIncidencia());
    }



    // Test para guardar una incidencia
    @Test
    public void testGuardarIncidencia() {
        
        soporteIncidencia.setIdSoporteIncidencia(1);
        
        when(soporteIncidenciaRepository.save(soporteIncidencia)).thenReturn(soporteIncidencia);
        
        SoporteIncidencia saved = soporteIncidenciaService.guardarIncidencia(soporteIncidencia);
        
        assertNotNull(saved); // IMPORTANTE: asegurarse que no sea null
        assertEquals(1, saved.getIdSoporteIncidencia());
    }



    /*@Test
    public void testActualizarIncidenciaExistente() {
        int id = 1;

        // Incidencia existente simulada (ya en la BD)
        SoporteIncidencia incidenciaExistente = new SoporteIncidencia();
        incidenciaExistente.setIdSoporteIncidencia(id);
        incidenciaExistente.setDetalles("Detalles antiguos");

        // Nueva información para actualizar
        SoporteIncidencia nuevaIncidencia = new SoporteIncidencia();
        nuevaIncidencia.setDetalles("Detalles actualizados");
        nuevaIncidencia.setFechaInicioIncidencia(LocalDateTime.now());
        nuevaIncidencia.setFechaTerminoIncidencia(LocalDateTime.now().plusDays(2));
        nuevaIncidencia.setSoporteSistema(new SoporteSistema()); // simula soporte asignado

        // Mock: findById() retorna la incidencia existente
        when(soporteIncidenciaRepository.findById(id)).thenReturn(Optional.of(incidenciaExistente));

        // Mock: save() retorna la incidencia actualizada
        when(soporteIncidenciaRepository.save(any(SoporteIncidencia.class)))
            .thenAnswer(invocation -> invocation.getArgument(0)); // devuelve lo que se le pasó

        // Ejecutar
        SoporteIncidencia actualizada = soporteIncidenciaService.actualizarIncidencia(id, nuevaIncidencia);

        // Verificaciones
        assertNotNull(actualizada);
        assertEquals("Detalles actualizados", actualizada.getDetalles());
        assertEquals(nuevaIncidencia.getFechaInicioIncidencia(), actualizada.getFechaInicioIncidencia());
        assertEquals(nuevaIncidencia.getFechaTerminoIncidencia(), actualizada.getFechaTerminoIncidencia());
        assertEquals(nuevaIncidencia.getSoporteSistema(), actualizada.getSoporteSistema());

        // Verifica que se haya llamado a save()
        verify(soporteIncidenciaRepository).save(incidenciaExistente);   // <----- ME QUEDÉ NE ESTA PARTE DEL CODIGO
    }*/


    

}
