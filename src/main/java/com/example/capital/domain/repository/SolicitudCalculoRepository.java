package com.example.capital.domain.repository;

import com.example.capital.domain.model.SolicitudCalculoEntity;

import java.util.List;
import java.util.Optional;

public interface SolicitudCalculoRepository {
    Optional<SolicitudCalculoEntity> findById(Long id);
    List<SolicitudCalculoEntity> findAll();
    SolicitudCalculoEntity save(SolicitudCalculoEntity product);
}
