package com.meowing.EduTech.service;


import com.meowing.EduTech.model.Foro;
import com.meowing.EduTech.repository.ForoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//** SERVICIO DEL MICROSERVICIO 5.- COMUNICACION Y FOROS**
@Service
@Transactional
public class ForoService {

    @Autowired
    private ForoRepository foroRepository;

    public List<Foro> obtenerrForos() { return foroRepository.findAll(); }

    public Foro obtenerForo(int id) {return foroRepository.findById(id).get();}

    public Foro crearForo(Foro foro) {return foroRepository.save(foro);}

    public void eliminarForo(int id) {foroRepository.deleteById(id);}

    // Obtener foro por seccion
    public List<Foro> forosBySeccion(Integer id_Seccion){
        return foroRepository.findBySeccion_idSeccion(id_Seccion);
    }
}
