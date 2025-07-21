package com.meowing.EduTech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.meowing.EduTech.model.Curso;
import com.meowing.EduTech.model.Pago;
import com.meowing.EduTech.model.TipoUsuario;
import com.meowing.EduTech.model.Usuario;
import com.meowing.EduTech.repository.PagoRepository;
import com.meowing.EduTech.service.PagoService;






@SpringBootTest
public class PagoServiceTest {


    @Autowired
    private PagoService pagoService;


    @MockBean
    private PagoRepository pagoRepository;


    private TipoUsuario tipoUsuario;
    private Usuario usuario;
    private Curso curso;
    private Pago pago;



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

        curso = new Curso();
        curso.setIdCurso(1);
        curso.setNombre("Java Spring Boot 3");
        curso.setFecha_inicio(null);
        curso.setFecha_termino(null);
        curso.setPrecio(15.0);

        pago = new Pago();
        pago.setIdPago(1);
        pago.setFecha(LocalDateTime.now());
        pago.setComprobante("asd22231##sdad56");
        pago.setUsuario(usuario);
        pago.setCurso(curso);

    }


    // Test para guardar un pago.
    @Test
    public void testGuardarPago() {

        // Asigna el valor 1 al atributo idPago del objeto pago.
        pago.setIdPago(1);

        // Cuando se llame al método save(pago) del pagoRepository, devuelve el mismo objeto pago.
        when(pagoRepository.save(pago)).thenReturn(pago);

        // Llama al método guardarPago() del servicio pagoService, pasando el objeto pago, 
        // y guarda el resultado en la variable saved.
        Pago saved = pagoService.guardarPago(pago);

        // Verifica que el objeto saved no sea null.
        assertNotNull(saved);
        // Verifica que el ID del pago guardado saved.getIdPago() sea igual a 1.
        assertEquals(1, saved.getIdPago());
    }



    // Test para listar todos los pagos.
    @Test
    public void testObtenerPagos() {

        // Cuando se llame al método findAll() del pagoRepository, devuelve una lista que 
        // contiene el objeto pago.
        when(pagoRepository.findAll()).thenReturn(List.of(pago));


        // Llama al método obtenerPagos() del servicio pagoService y 
        // guarda el resultado (una lista de objetos Pago) en la variable pagos.
        List<Pago> pagos = pagoService.obtenerPagos();


        // Verifica que la lista pagos no sea null.
        assertNotNull(pagos);
        // Verifica que la lista pagos tenga exactamente 1 elemento.
        assertEquals(1, pagos.size());
    }



    // Test para obtener un pago por su id.
    @Test
    public void testObtenerPorIdPago() {
        int id = 1;

        // Cuando se llame al método findById(id) del pagoRepository, 
        // devuelve un Optional que contiene el objeto pago.
        when(pagoRepository.findById(id)).thenReturn(Optional.of(pago));
        

        //  Llama al método obtenerPorIdPago(id) del servicio pagoService para buscar un pago por su ID,
        //  y guarda el resultado en la variable found.
        Pago found = pagoService.obtenerPorIdPago(id);
        
        
        // Verifica que el objeto found no sea null (es decir, que se haya encontrado un pago).
        assertNotNull(found);
        // Verifica que el ID del pago encontrado sea igual al id buscado.  
        assertEquals(id, found.getIdPago());

    }

}
