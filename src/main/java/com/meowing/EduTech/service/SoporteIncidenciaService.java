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



    // Este método lista las incidencias 
    public List<SoporteIncidencia> obtenerIncidencias(){
        return soporteIncidenciaRepository.findAll();
    }


    // Este método obtiene solo una incidencia por el parametro id
    public SoporteIncidencia obtenerIncidenciaPorId(int id) {
    return soporteIncidenciaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Incidencia no encontrada con ID: " + id));
    }


    // Este método guarda una incidencia
    public SoporteIncidencia guardarIncidencia(SoporteIncidencia incidencia) {
        return soporteIncidenciaRepository.save(incidencia);
    }
    

    // Este método actualiza una incidencia existente
    public SoporteIncidencia actualizarIncidencia(int id, SoporteIncidencia nuevaIncidencia) {
        return soporteIncidenciaRepository.findById(id).map(incidenciaExistente -> {
            incidenciaExistente.setDetalles(nuevaIncidencia.getDetalles());
            incidenciaExistente.setFechaInicioIncidencia(nuevaIncidencia.getFechaInicioIncidencia());
            incidenciaExistente.setFechaTerminoIncidencia(nuevaIncidencia.getFechaTerminoIncidencia());
            incidenciaExistente.setSoporteSistema(nuevaIncidencia.getSoporteSistema());

            return soporteIncidenciaRepository.save(incidenciaExistente);
        }).orElseThrow(() -> new RuntimeException("Incidencia con ID " + id + " no encontrada."));
    }

<<<<<<< HEAD


    public List<SoporteIncidencia> obtenerIncidenciaPorIdSoporteSistema(int idSoporteSistema) {
=======
    public List<SoporteIncidencia> obtenerIncidenciaPorSoporteId(Integer idSoporteSistema) {
>>>>>>> d0d38f5c71f556f31b151d214d148412c5342b1c
        return soporteIncidenciaRepository.findBySoporteSistema_IdSoporteSistema(idSoporteSistema);
    }

}
