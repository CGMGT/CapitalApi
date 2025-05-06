package com.example.capital.domain.repository;

import com.example.capital.domain.model.UsuarioEntity;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    Optional<UsuarioEntity> findById(Long id);
    List<UsuarioEntity> findAll();
    UsuarioEntity save(UsuarioEntity product);
}
