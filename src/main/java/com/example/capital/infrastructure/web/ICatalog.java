package com.example.capital.infrastructure.web;

import com.example.capital.application.dto.DataTableRequestDto;
import org.springframework.http.ResponseEntity;

public interface ICatalog<T> {

    ResponseEntity findAll();

    ResponseEntity findAllByPage(DataTableRequestDto dataTableRequestDto);

    ResponseEntity findById(Long id);

    ResponseEntity create(T entity, Long requesterId);


}
