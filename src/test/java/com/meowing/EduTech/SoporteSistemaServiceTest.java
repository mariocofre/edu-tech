package com.meowing.EduTech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.meowing.EduTech.model.SoporteIncidencia;
import com.meowing.EduTech.model.SoporteSistema;
import com.meowing.EduTech.model.TipoUsuario;
import com.meowing.EduTech.model.Usuario;
import com.meowing.EduTech.repository.SoporteSistemaRepository;
import com.meowing.EduTech.service.SoporteSistemaService;





@SpringBootTest
public class SoporteSistemaServiceTest {

    // Inyecta el servicio de SoporteSistema para ser probado
    @Autowired
    private SoporteSistemaService soporteSistemaService;


    // Crea un mock del repository de SoporteSistema para simular su comportamiento
    @MockBean
    private SoporteSistemaRepository soporteSistemaRepository;


    private TipoUsuario tipoUsuario;
    private Usuario usuario;
    private SoporteSistema soporte;


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

    }


    // Test para obtener todos los soportes
    @Test
    public void testObtenerSoportes() {

        // Define el comportamiento del mock: Cuando se llame a obtenerSoportes(), devuelve una lista con un SoporteSistema.
        when(soporteSistemaRepository.findAll()).thenReturn(List.of(soporte));

        // Llama al método obtenerSoportes() del servicio
        List<SoporteSistema> soportes = soporteSistemaService.obtenerSoportes();

        // Verifica que la lista devuelta no sea nula y contenga exactamente un SoporteSistema.
        assertNotNull(soportes);
        assertEquals(1, soportes.size());
    }


    // Test para obtener un solo soporte por su id.
    @Test
    public void testObtenerSoportePorId() {
        int id = 1;

        // Define el comportamiento del mock: cuando se llame a findById() con "1", devuelve un Soporte opcional.
        when(soporteSistemaRepository.findById(id)).thenReturn(Optional.of(soporte));

        // Llama al método obtenerSoportePorId() del service 
        SoporteSistema found = soporteSistemaService.obtenerSoportePorId(id);

        // Verifica que el SoporteSistema devuelta no sea nulo y que el id coincida con el id esperado.
        assertNotNull(found);
        assertEquals(id, found.getIdSoporteSistema());
    }
    

    // Test para agregar un soporte
    @Test
    public void testAgregarSoporte() {

        soporte.setIdSoporteSistema(1);

        when(soporteSistemaRepository.save(soporte)).thenReturn(soporte);

        SoporteSistema saved = soporteSistemaService.agregarSoporte(soporte);

        assertNotNull(saved);
        assertEquals(1, saved.getIdSoporteSistema());
    }


    // Test para obtener un soporte por el id de usuario
    @Test
    public void testObtenerSoportePorIdUsuario() {
        int idUsuario = 2;

        usuario.setIdUsuario(idUsuario);

        List<SoporteSistema> listaSimulada = List.of(soporte);


        when(soporteSistemaRepository.findByUsuario_idUsuario(idUsuario))
            .thenReturn(listaSimulada);

        List<SoporteSistema> resultado = soporteSistemaService.obtenerSoportePorIdUsuario(idUsuario);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        resultado.get(0).getUsuario().equals(usuario);

        
        verify(soporteSistemaRepository).findByUsuario_idUsuario(idUsuario);
    }
}
