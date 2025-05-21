package com.meowing.EduTech.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meowing.EduTech.model.TipoIncidencia;
import com.meowing.EduTech.repository.TipoIncidenciaRepository;

import jakarta.transaction.Transactional;

//** CLASE DEL MICROSERVICIO 2.- MONITOREO DEL SISTEMA **
@Service
@Transactional
public class TipoIncidenciaService {


    @Autowired
    private TipoIncidenciaRepository tipoIncidenciaRepository;

    public List<TipoIncidencia> obtenerTipoIncidencias() {
        return tipoIncidenciaRepository.findAll();
    }

    public TipoIncidencia obtenerTipoIncidencia(int id) {
        return tipoIncidenciaRepository.findById(id).get();
    }

    public TipoIncidencia agregarTipoIncidencia(TipoIncidencia tipo) {
        return tipoIncidenciaRepository.save(tipo);
    }
    

    


}
