package com.example.capital.application.service;

import com.example.capital.application.dto.DataTableRequestDto;
import com.example.capital.application.dto.PaginatedDataDto;
import com.example.capital.domain.model.HistorialCalculoEntity;
import com.example.capital.domain.repository.HistorialCalculoRepository;
import com.example.capital.util.exception.RequesterNotFoundException;
import com.example.capital.util.exception.ResourceCreateException;
import com.example.capital.util.exception.ResourceNotFoundException;
import com.example.capital.util.exception.ResourcesNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialCalculoService implements ICatalog<HistorialCalculoEntity>{
    private final HistorialCalculoRepository historialCalculoRepository;

    public HistorialCalculoService(HistorialCalculoRepository historialCalculoRepository) {
        this.historialCalculoRepository = historialCalculoRepository;
    }


    public List<HistorialCalculoEntity> getAllHistorialCalculo() {
        return historialCalculoRepository.findAll();
    }

    public HistorialCalculoEntity addHistorialCalculo(HistorialCalculoEntity historialCalculoEntity) {
        return historialCalculoRepository.save(historialCalculoEntity);
    }

    @Override
    public List<HistorialCalculoEntity> findAll() throws ResourcesNotFoundException {
        try {
            return (List<HistorialCalculoEntity>) this.historialCalculoRepository.findAll();
        } catch (Exception ex) {

            throw new ResourcesNotFoundException();
        }
    }

    @Override
    public PaginatedDataDto<HistorialCalculoEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        return null;
    }

    @Override
    public Optional <HistorialCalculoEntity> findById(Long id) throws ResourceNotFoundException {
        Optional <HistorialCalculoEntity> entidad = this.historialCalculoRepository.findById(id);

        if (entidad == null) {
            throw new ResourceNotFoundException();
        }

        return entidad;
    }

    @Override
    public HistorialCalculoEntity create(HistorialCalculoEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        return null;
    }
}
