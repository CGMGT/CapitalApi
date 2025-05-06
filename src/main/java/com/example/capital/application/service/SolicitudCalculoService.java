package com.example.capital.application.service;

import com.example.capital.domain.model.SolicitudCalculoEntity;
import com.example.capital.domain.repository.SolicitudCalculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudCalculoService {
    private final SolicitudCalculoRepository solicitudCalculoRepository;

    public SolicitudCalculoService(SolicitudCalculoRepository solicitudCalculoRepository) {
        this.solicitudCalculoRepository = solicitudCalculoRepository;
    }

    public Optional<SolicitudCalculoEntity> getSolicitudCalculoById(Long id) {
        return solicitudCalculoRepository.findById(id);
    }

    public List<SolicitudCalculoEntity> getAllSolicitudCalculo() {
        return solicitudCalculoRepository.findAll();
    }

    public SolicitudCalculoEntity addSolicitudCalculo(SolicitudCalculoEntity solicitudCalculoEntity) {
        return solicitudCalculoRepository.save(solicitudCalculoEntity);
    }
}
