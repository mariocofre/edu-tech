package com.meowing.EduTech.service;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meowing.EduTech.model.SoporteSistema;
import com.meowing.EduTech.repository.SoporteSistemaRepository;

import jakarta.transaction.Transactional;



//** CLASE DEL MICROSERVICIO 2.- MONITOREO DEL SISTEMA **
@Service
@Transactional
public class SoporteSistemaService {


    @Autowired
    private SoporteSistemaRepository soporteSistemaRepository;


    public List<SoporteSistema> obtenerSoportes() {
        return soporteSistemaRepository.findAll();
    }

    public SoporteSistema obtenerSoportePorId(int id) {
        return soporteSistemaRepository.findById(id).get();
    }

    public SoporteSistema agregarSoporte(SoporteSistema soporte) {
        return soporteSistemaRepository.save(soporte);
    }
    

    public List<SoporteSistema> obtenerSoportePorIdUsuario(Integer idUsuario) {
        return soporteSistemaRepository.findByUsuario_idUsuario(idUsuario);
    }
    

}
