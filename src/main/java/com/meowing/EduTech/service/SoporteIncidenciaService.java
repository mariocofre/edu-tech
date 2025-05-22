package com.meowing.EduTech.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.meowing.EduTech.model.SoporteIncidencia;
import com.meowing.EduTech.model.SoporteSistema;
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
            incidenciaExistente.setFechaInicioIncidencia(nuevaIncidencia.getFechaInicioIncidencia());
            incidenciaExistente.setFechaTerminoIncidencia(nuevaIncidencia.getFechaTerminoIncidencia());
            incidenciaExistente.setSoporteSistema(nuevaIncidencia.getSoporteSistema());

            return soporteIncidenciaRepository.save(incidenciaExistente);
        }).orElseThrow(() -> new RuntimeException("Incidencia con ID " + id + " no encontrada."));
    }

    public List<SoporteIncidencia> obtenerIncidenciaPorSoporte(int idSoporteSistema) {
        return soporteIncidenciaRepository.findBySoporteSistema_IdSoporteSistema(idSoporteSistema);
    }



}
