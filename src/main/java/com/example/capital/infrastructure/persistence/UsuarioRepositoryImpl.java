package com.example.capital.infrastructure.persistence;

import com.example.capital.domain.model.UsuarioEntity;
import com.example.capital.domain.repository.UsuarioRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositoryImpl extends JpaRepository<UsuarioEntity, Long>, UsuarioRepository {
    @Override
    Optional<UsuarioEntity> findById(Long id);

    @Override
    List<UsuarioEntity> findAll();

    @Override
    UsuarioEntity save(UsuarioEntity usuarioEntity);

}
