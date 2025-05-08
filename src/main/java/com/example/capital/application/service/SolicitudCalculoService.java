package com.example.capital.application.service;

import com.example.capital.application.dto.DataTableRequestDto;
import com.example.capital.application.dto.PaginatedDataDto;
import com.example.capital.domain.model.HistorialCalculoEntity;
import com.example.capital.domain.model.SolicitudCalculoEntity;
import com.example.capital.domain.model.UsuarioEntity;
import com.example.capital.domain.repository.HistorialCalculoRepository;
import com.example.capital.domain.repository.SolicitudCalculoRepository;
import com.example.capital.domain.repository.UsuarioRepository;
import com.example.capital.util.exception.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.Timestamp;
import java.util.Date;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SolicitudCalculoService implements ICatalog<SolicitudCalculoEntity>{
    private final SolicitudCalculoRepository solicitudCalculoRepository;
    private final HistorialCalculoRepository historialCalculoRepository;
    private final UsuarioRepository usuarioRepository;
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

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

    public HistorialCalculoEntity calcular(SolicitudCalculoEntity entity, Long requesterId) throws RequesterNotFoundException, ValidatorException {
        Optional<UsuarioEntity> requester = this.usuarioRepository.findById(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }
        entity.setUsuarioGrabacion(requester.get().getId().intValue());
        entity.setFechaGrabacion(new Timestamp(new Date().getTime()));
        Set<ConstraintViolation<SolicitudCalculoEntity>> violations = validator.validate(entity);
        try {
            if (!violations.isEmpty()) {
                StringBuilder errors = new StringBuilder();
                for (ConstraintViolation<SolicitudCalculoEntity> violation : violations) {
                    errors.append(violation.getMessage()).append("; ");
                }
                throw new ValidatorException(errors.toString());
            }
            return this.calcularInteresesAnuales(this.solicitudCalculoRepository.save(entity));
        } catch (Exception ex) {

            throw new ValidatorException(ex.getMessage());
        }
    }

    public JSONArray calcular(double montoInicial, double tasaInteres, short anios, String tipo){
        JSONArray interesArray = new JSONArray();
        for (int anio = 1; anio <= anios; anio++) {
            double montoCalculado = (tipo.equals("SIMPLE"))?montoInicial + (montoInicial * tasaInteres/100 * anio):montoInicial * Math.pow(1 + tasaInteres/100, anio);

            JSONObject interes = new JSONObject();
            interes.put("anio", anio);
            interes.put("monto", montoCalculado);

            interesArray.put(interes);
        }
        return interesArray;
    }

    public HistorialCalculoEntity calcularInteresesAnuales(SolicitudCalculoEntity entity) throws ResourceCreateException, JsonProcessingException {
        JSONArray interesSimpleArray = calcular(entity.getMontoInicial(),entity.getTasaInteres(),entity.getAnios(),"SIMPLE");
        JSONArray interesCompuestoArray = calcular(entity.getMontoInicial(),entity.getTasaInteres(),entity.getAnios(),"COMPUESTO");
        HistorialCalculoEntity historialCalculoEntity = new HistorialCalculoEntity();
        historialCalculoEntity.setId(entity.getId());
        historialCalculoEntity.setFecha(entity.getFechaGrabacion());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode interesSimple = mapper.readTree(interesSimpleArray.toString());
        JsonNode interesCompuesto = mapper.readTree(interesCompuestoArray.toString());
        historialCalculoEntity.setInteresCompuesto(interesCompuesto);
        historialCalculoEntity.setInteresSimple(interesSimple);

        try {
            return this.historialCalculoRepository.save(historialCalculoEntity);
        } catch (Exception ex) {

            throw new ResourceCreateException();
        }
    }
}
