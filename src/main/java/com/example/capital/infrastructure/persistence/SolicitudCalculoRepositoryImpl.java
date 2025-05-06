package com.example.capital.infrastructure.persistence;

import com.example.capital.domain.model.SolicitudCalculoEntity;
import com.example.capital.domain.repository.SolicitudCalculoRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SolicitudCalculoRepositoryImpl extends JpaRepository<SolicitudCalculoEntity, Long>, SolicitudCalculoRepository {
    @Override
    Optional<SolicitudCalculoEntity> findById(Long id);

    @Override
    List<SolicitudCalculoEntity> findAll();

    @Override
    SolicitudCalculoEntity save(SolicitudCalculoEntity solicitudCalculoEntity);
}
