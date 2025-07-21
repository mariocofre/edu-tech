package com.meowing.EduTech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.meowing.EduTech.model.TipoUsuario;
import com.meowing.EduTech.repository.TipoUsuarioRepository;
import com.meowing.EduTech.service.TipoUsuarioService;







@SpringBootTest
public class TipoUsuarioServiceTest {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @MockBean
    private TipoUsuarioRepository tipoUsuarioRepository;

    
    private TipoUsuario tipoUsuario;


    @BeforeEach
    public void setUp() {


        tipoUsuario = new TipoUsuario();
        tipoUsuario.setIdTipoUsuario(1);
        tipoUsuario.setTipoUsuario("Docente");
    }


    // Test para guardar un TipoUsuario
    @Test
    public void testGuardarTipoUsuario() {

        // Asigna el valor 1 al atributo idTipoUsuario del objeto tipoUsuario
        when(tipoUsuarioRepository.save(tipoUsuario)).thenReturn(tipoUsuario);

        // Llama al método guardarTipoUsuario() del service, pasando 
        // el objeto tipoUsuario y guarda el resultado en la variable saved
        TipoUsuario saved = tipoUsuarioService.guardarTipoUsuario(tipoUsuario);

        // Verifica que el objeto saved no sea null
        assertNotNull(saved);
        // Verifica que el id del objeto tipoUsuario guardado 
        // saved.getIdTipoUsuario sea igual a 1
        assertEquals(1, saved.getIdTipoUsuario());
    }

    

    // Test para listar todos los tipoUsuario
    @Test
    public void testObtenerTipoUsuarios() {

        // Cuando se llame al método findAll() del repository, 
        // devuelve una lista que contiene el objeto pago
        when(tipoUsuarioRepository.findAll()).thenReturn(List.of(tipoUsuario));


        // Llama al método obtenerTipoUsuarios() del service y guarda
        // el resultado "una lista de objetos tipoUsuario" en la variable
        // tiposUsuarios
        List<TipoUsuario> tiposUsuarios = tipoUsuarioService.obtenerTipoUsuarios();


        // Verifica que la lista tiposUsuarios no sea null
        assertNotNull(tiposUsuarios);
        // Verifica que la lista tiposUsuarios tenga exactamente 1 elemento
        assertEquals(1, tiposUsuarios.size());
    }


    // Test para obtener un tipoUsuario por su id
    @Test 
    public void testObtenerTipoUsuario() {
        int id = 1;

        // Cuando se llame al método findById(id) del repository,
        // devuelve un Optional que contiene el objeto tipoUsuario
        when(tipoUsuarioRepository.findById(id)).thenReturn(Optional.of(tipoUsuario));


        // Llama al método obtenerTipoUsuario(id) del service
        // para buscar un tipoUsuario por su id, y guarda el 
        // resultado en la variable found
        TipoUsuario found = tipoUsuarioService.obtenerTipoUsuario(id);


        // Verifica que el objeto found no sea null "es decir que se haya encontrado un tipoUsuario"
        assertNotNull(found);
        // Verifica que el id del tipoUsuario encontrado sea igual al id buscado
        assertEquals(id, found.getIdTipoUsuario());
    }



    // Test para eliminar un tipoUsuario
    @Test
    public void testEliminarTipoUsuario() {
        int id = 1;

        doNothing().when(tipoUsuarioRepository).deleteById(id);

        tipoUsuarioService.eliminarTipoUsuario(id);

        verify(tipoUsuarioRepository, times(1)).deleteById(id);

    }

}
