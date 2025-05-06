package com.example.capital.infrastructure.persistence;

import com.example.capital.domain.model.HistorialCalculoEntity;
import com.example.capital.domain.repository.HistorialCalculoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface HistorialCalculoRepositoryImpl extends JpaRepository<HistorialCalculoEntity, Long>, JpaSpecificationExecutor<HistorialCalculoEntity>, HistorialCalculoRepository {

    @Override
    Optional<HistorialCalculoEntity> findById(Long id);

    @Override
    List<HistorialCalculoEntity> findAll();

    @Override
    HistorialCalculoEntity save(HistorialCalculoEntity historialCalculoEntity);
}
