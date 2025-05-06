package com.example.capital.domain.repository;

import com.example.capital.domain.model.HistorialCalculoEntity;

import java.util.List;
import java.util.Optional;


public interface HistorialCalculoRepository {
    Optional<HistorialCalculoEntity> findById(Long id);
    List<HistorialCalculoEntity> findAll();
    HistorialCalculoEntity save(HistorialCalculoEntity historialCalculoEntity);
}
