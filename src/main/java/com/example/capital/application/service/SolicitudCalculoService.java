package com.example.capital.application.service;

import com.example.capital.application.dto.DataTableRequestDto;
import com.example.capital.application.dto.PaginatedDataDto;
import com.example.capital.domain.model.HistorialCalculoEntity;
import com.example.capital.domain.model.SolicitudCalculoEntity;
import com.example.capital.domain.model.UsuarioEntity;
import com.example.capital.domain.repository.HistorialCalculoRepository;
import com.example.capital.domain.repository.SolicitudCalculoRepository;
import com.example.capital.domain.repository.UsuarioRepository;
import com.example.capital.util.exception.RequesterNotFoundException;
import com.example.capital.util.exception.ResourceCreateException;
import com.example.capital.util.exception.ResourceNotFoundException;
import com.example.capital.util.exception.ResourcesNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.Timestamp;
import java.util.Date;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudCalculoService implements ICatalog<SolicitudCalculoEntity>{
    private final SolicitudCalculoRepository solicitudCalculoRepository;
    private final HistorialCalculoRepository historialCalculoRepository;
    private final UsuarioRepository usuarioRepository;

    public SolicitudCalculoService(SolicitudCalculoRepository solicitudCalculoRepository,
                                   UsuarioRepository usuarioRepository,
                                   HistorialCalculoRepository historialCalculoRepository) {
        this.solicitudCalculoRepository = solicitudCalculoRepository;
        this.usuarioRepository = usuarioRepository;
        this.historialCalculoRepository = historialCalculoRepository;
    }

    @Override
    public List<SolicitudCalculoEntity> findAll() throws ResourcesNotFoundException {
        try {
            return  this.solicitudCalculoRepository.findAll();
        } catch (Exception ex) {

            throw new ResourcesNotFoundException();
        }
    }

    @Override
    public PaginatedDataDto<SolicitudCalculoEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        return null;
    }

    @Override
    public Optional<SolicitudCalculoEntity> findById(Long id) throws ResourceNotFoundException {
        Optional <SolicitudCalculoEntity> entidad = this.solicitudCalculoRepository.findById(id);

        if (entidad == null) {
            throw new ResourceNotFoundException();
        }

        return entidad;
    }

    @Override
    public SolicitudCalculoEntity create(SolicitudCalculoEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        Optional<UsuarioEntity> requester = this.usuarioRepository.findById(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }
        try {
            return this.solicitudCalculoRepository.save(entity);
        } catch (Exception ex) {

            throw new ResourceCreateException();
        }
    }

    public HistorialCalculoEntity calcular(SolicitudCalculoEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        Optional<UsuarioEntity> requester = this.usuarioRepository.findById(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }
        entity.setUsuarioGrabacion(requester.get().getId().intValue());
        entity.setFechaGrabacion(new Timestamp(new Date().getTime()));
        try {
            return this.calcularInteresesAnuales(this.solicitudCalculoRepository.save(entity));
        } catch (Exception ex) {

            throw new ResourceCreateException();
        }
    }

    public HistorialCalculoEntity calcularInteresesAnuales(SolicitudCalculoEntity entity) throws ResourceCreateException, JsonProcessingException {
        JSONArray interesSimpleArray = new JSONArray();
        JSONArray interesCompuestoArray = new JSONArray();


        for (int anio = 1; anio <= entity.getAnios(); anio++) {
            double montoSimple = entity.getMontoInicial() + (entity.getMontoInicial() * entity.getTasaInteres()/100 * anio);
            double montoCompuesto = entity.getMontoInicial() * Math.pow(1 + entity.getTasaInteres()/100, anio);

            JSONObject simpleAnual = new JSONObject();
            simpleAnual.put("anio", anio);
            simpleAnual.put("monto", montoSimple);

            JSONObject compuestoAnual = new JSONObject();
            compuestoAnual.put("anio", anio);
            compuestoAnual.put("monto", montoCompuesto);

            interesSimpleArray.put(simpleAnual);
            interesCompuestoArray.put(compuestoAnual);
        }
        JSONObject interesSimpleObject = new JSONObject();
        JSONObject interesCompuestoObject = new JSONObject();
        interesSimpleObject.put("", interesSimpleArray);
        interesCompuestoObject.put("", interesCompuestoArray);
        HistorialCalculoEntity historialCalculoEntity = new HistorialCalculoEntity();
        historialCalculoEntity.setId(entity.getId());
        historialCalculoEntity.setFecha(entity.getFechaGrabacion());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode interesSimple = mapper.readTree(interesSimpleObject.toString());
        JsonNode interesCompuesto = mapper.readTree(interesCompuestoObject.toString());
        historialCalculoEntity.setInteresCompuesto(interesCompuesto);
        historialCalculoEntity.setInteresCompuesto(interesSimple);

        try {
            return this.historialCalculoRepository.save(historialCalculoEntity);
        } catch (Exception ex) {

            throw new ResourceCreateException();
        }
    }
}
