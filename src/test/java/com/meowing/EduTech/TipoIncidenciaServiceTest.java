package com.meowing.EduTech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.meowing.EduTech.model.TipoIncidencia;
import com.meowing.EduTech.repository.TipoIncidenciaRepository;
import com.meowing.EduTech.service.TipoIncidenciaService;



@SpringBootTest
public class TipoIncidenciaServiceTest {    


    @Autowired
    private TipoIncidenciaService tipoIncidenciaService;


    @MockBean
    private TipoIncidenciaRepository tipoIncidenciaRepository;


    private TipoIncidencia tipoIncidencia;




    @BeforeEach
    public void setUp(){

        tipoIncidencia = new TipoIncidencia();
        tipoIncidencia.setIdTipoIncidencia(1);
        tipoIncidencia.setTipo("Critico");

    }


    // Test para guardar un tipoincidencia
    @Test
    public void testGuardarTipoIncidencia() {

        // Asigna el valor 1 al atributo idTipoIncidencia del objeto TipoIncidencia.
        tipoIncidencia.setIdTipoIncidencia(1);

        // Cuando se llame al método save(tipoIncidencia) del tipoIncidenciaRepository, 
        // devuelve el mismo objeto TipoIncidencia.
        when(tipoIncidenciaRepository.save(tipoIncidencia)).thenReturn(tipoIncidencia);

        // Llama al método agregarTipoIncidencia() del servicio TipoIncidenciaService
        // pasando el objeto tipoIncidencia, y guarda el resultado en la variable saved.
        TipoIncidencia saved = tipoIncidenciaService.agregarTipoIncidencia(tipoIncidencia);

        // Verifica que el objeto saved no sea null
        assertNotNull(saved);
        // Verifica que el ID del objeto tipoIncidencia agregado 
        // saved.getIdTipoIncidencia() sea igual a 1
        assertEquals(1, saved.getIdTipoIncidencia());
    }


    // Test para listar todos los TipoIncidencia
    @Test
    public void testObtenerTipoIncidencias() {

        // Cuando se llame al método findAll() del tipoIncidenciaRepository,
        // devuelve una lista que contiene el objeto tipoIncidencia.
        when(tipoIncidenciaRepository.findAll()).thenReturn(List.of(tipoIncidencia));


        // Llama al método obtenerTipoIncidencias() del servicio 
        // tipoIncidenciaService y guarda el resultado 
        // (una lista de objetos tipoIncidencia) en la variable tiposIncidencias
        List<TipoIncidencia> tiposIncidencias = tipoIncidenciaService.obtenerTipoIncidencias();


        // Verifica que la lista tiposIncidencias no sea null
        assertNotNull(tiposIncidencias);
        // Verifica que la lista tiposIncidencias tenga exactamente 1 elemento
        assertEquals(1, tiposIncidencias.size());
    }


    // Test para obneter un TipoIncidencia por su id
    @Test
    public void testObtenerTipoIncidencia() {
        int id = 1;


        // Cuando se llame al método findById(id) del repository,
        // devuelve un Optional que contiene el objeto tipoIncidencia
        when(tipoIncidenciaRepository.findById(id)).thenReturn(Optional.of(tipoIncidencia));


        // Llama al método obtenerTipoIncidencia(id) del service
        // para buscar un tipoIncidencia por su id,
        // y guarda el resultado en la variable "found"
        TipoIncidencia found = tipoIncidenciaService.obtenerTipoIncidencia(id);


        // Verifica que el objeto found no sea null 
        // (es decir, que se haya encontrado un tipoIncidencia)
        assertNotNull(found);
        // Verifica que el id del tipoIncidencia encontrado sea igual
        // al id buscado
        assertEquals(id, found.getIdTipoIncidencia());
    }
}
