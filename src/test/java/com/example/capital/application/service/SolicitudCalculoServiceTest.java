package com.example.capital.application.service;

import com.example.capital.domain.model.CalculoEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SolicitudCalculoServiceTest {


    @InjectMocks
    private SolicitudCalculoService service;

    @Test
    void testCalcularInteres() throws  JsonProcessingException {



        ObjectMapper mapper = new ObjectMapper();
        List<CalculoEntity> compuesto = new ArrayList<>();
        for (JsonNode node : mapper.readTree(service.calcular(750.0,5.0, (short) 5,"COMPUESTO").toString())) {
            CalculoEntity interesCompuesto = new CalculoEntity(node.get("monto").asDouble(), node.get("anio").shortValue());
            compuesto.add(interesCompuesto);
        }

        List<CalculoEntity> simple = new ArrayList<>();
        for (JsonNode node : mapper.readTree(service.calcular(750.0,5.0, (short) 5,"SIMPLE").toString())) {
            CalculoEntity interesSimple = new CalculoEntity(node.get("monto").asDouble(), node.get("anio").shortValue());
            simple.add(interesSimple);
        }

        assertEquals(5, compuesto.size());
        assertEquals(5, simple.size());
        assertEquals(787.5, compuesto.get(0).getMonto());
        assertEquals(825, simple.get(1).getMonto());
    }
}
