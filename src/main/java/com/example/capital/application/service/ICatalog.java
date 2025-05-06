package com.example.capital.application.service;

import com.example.capital.application.dto.DataTableRequestDto;
import com.example.capital.application.dto.PaginatedDataDto;
import com.example.capital.util.exception.RequesterNotFoundException;
import com.example.capital.util.exception.ResourceCreateException;
import com.example.capital.util.exception.ResourceNotFoundException;
import com.example.capital.util.exception.ResourcesNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ICatalog<T> {

    List<T> findAll() throws ResourcesNotFoundException;

    PaginatedDataDto<T> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException;

    Optional<T> findById(Long id) throws ResourceNotFoundException;

    T create(T entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException;

}
