package com.meowing.EduTech;

import com.meowing.EduTech.model.TipoUsuario;
import com.meowing.EduTech.model.Usuario;
import com.meowing.EduTech.repository.UsuarioRepository;
import com.meowing.EduTech.service.UsuarioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    private UsuarioRepository usuarioRepository;

    private TipoUsuario tipoUsuario;
    private Usuario usuario;

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

    }

    // Test para obtener todas las incidencias
    @Test
    public void testFindAll() {

        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con un Usuario.
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        // Llama al método obtenerUsuarios() del servicio.
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();

        // Verifica que la lista devuelta no sea nula y contenga exactamente un Usuario.
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
    }

    @Test
    public void testObtenerUsuarioRun() {
        String run = "12345678-9";

        // Define el comportamiento del mock: cuando se llame a findById() con "1", devuelve una Carrera opcional.
        when(usuarioRepository.findByRunUsuario(run)).thenReturn(usuario);

        // Llama al método findByCodigo() del servicio.
        Usuario found = usuarioService.obtenerUsuario(run);

        // Verifica que la Carrera devuelta no sea nula y que su código coincida con el código esperado.
        assertNotNull(found);
        assertEquals(run, found.getRunUsuario());
        assertEquals("Juan", found.getNombreUsuario());
    }

    @Test
    public void testGuardarUsuario() {
        Usuario usuario = new Usuario(1, "12345678-9", "Juan", "Pérez", "claveSegura123", "juan.perez@correo.com", tipoUsuario);

        // Define el comportamiento del mock: cuando se llame a save(), devuelve la Carrera proporcionada.
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Llama al método save() del servicio.
        Usuario saved = usuarioService.guardarUsuario(usuario);

        // Verifica que la Carrera guardada no sea nula.
        // Verifica que su nombre coincida con el nombre esperado.
        // Verifica que su run coincida con el run esperado.
        assertNotNull(saved);
        assertEquals("Juan", saved.getNombreUsuario());
        assertEquals("12345678-9", saved.getRunUsuario());
    }

    @Test
    public void testDeleteByRun() {
        String run = "12345678-9";

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(usuarioRepository).deleteByRunUsuario(run);

        // Llama al método deleteByCodigo() del servicio.
        usuarioService.eliminarUsuario(run);

        // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el código proporcionado.
        verify(usuarioRepository, times(1)).deleteByRunUsuario(run);
    }

    @Test
    public void testObtenerUsuarioByTipoUsuario() {
        // ID del tipo de usuario a buscar
        Integer idTipoUsuario = 1;

        // Simulamos que el repositorio retorna una lista con ese idTipoUsuario
        when(usuarioRepository.findByTipoUsuario_IdTipoUsuario(idTipoUsuario)).thenReturn(List.of(usuario));

        // Llamamos al servicio
        List<Usuario> resultado = usuarioService.obtenerUsuarioByTipoUsuario(idTipoUsuario);

        // Verifica que la Carrera guardada no sea nula y que su nombre coincida con el nombre esperado.
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Soporte", resultado.get(0).getTipoUsuario().getTipoUsuario());
        assertEquals("Juan", resultado.get(0).getNombreUsuario());
    }

}




























