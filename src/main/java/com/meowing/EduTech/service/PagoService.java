package com.meowing.EduTech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meowing.EduTech.model.Pago;
import com.meowing.EduTech.repository.PagoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;


    public Pago guardarPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    public List<Pago> obtenerPagos() {
        return pagoRepository.findAll();
    }

    public Pago obtenerPago(int idPago) {
        return pagoRepository.findById(idPago).get();
    }
}
