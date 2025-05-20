package com.meowing.EduTech.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.meowing.EduTech.model.SoporteIncidencia;
import com.meowing.EduTech.repository.SoporteIncidenciaRepository;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional


//** CLASE DEL MICROSERVICIO 2.- MONITOREO DEL SISTEMA **
public class SoporteIncidenciaService {


    @Autowired
    private SoporteIncidenciaRepository soporteIncidenciaRepository;

    public List<SoporteIncidencia> obtenerIncidencias(){
        return soporteIncidenciaRepository.findAll();
    }

    public SoporteIncidencia obtenerIncidencia(int id) {
        return soporteIncidenciaRepository.findById(id).get();
    }

    public SoporteIncidencia guardarIncidencia(SoporteIncidencia incidencia) {
        return soporteIncidenciaRepository.save(incidencia);
    }
    

    public SoporteIncidencia actualizarIncidencia(int id, SoporteIncidencia nuevaIncidencia) {
        return soporteIncidenciaRepository.findById(id).map(incidenciaExistente -> {
            incidenciaExistente.setDetalles(nuevaIncidencia.getDetalles());
            incidenciaExistente.setFecha_inicio_incidencia(nuevaIncidencia.getFecha_inicio_incidencia());
            incidenciaExistente.setFecha_termino_incidencia(nuevaIncidencia.getFecha_termino_incidencia());
            incidenciaExistente.setId_soporte_sistema(nuevaIncidencia.getId_soporte_sistema());
            // incidenciaExistente.setId_tipo_incidencia(nuevaIncidencia.getId_tipo_incidencia()); // si la clase ya existe

            return soporteIncidenciaRepository.save(incidenciaExistente);
        }).orElseThrow(() -> new RuntimeException("Incidencia con ID " + id + " no encontrada."));
    }





}
