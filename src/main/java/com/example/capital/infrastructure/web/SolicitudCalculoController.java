package com.example.capital.infrastructure.web;

import com.example.capital.application.service.SolicitudCalculoService;
import com.example.capital.domain.model.SolicitudCalculoEntity;
import com.example.capital.util.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api/calcular-interes")
public class SolicitudCalculoController{

    private final SolicitudCalculoService solicitudCalculoService;

    public SolicitudCalculoController(SolicitudCalculoService solicitudCalculoService) {
        this.solicitudCalculoService = solicitudCalculoService;
    }

    @GetMapping
    public ResponseEntity findAll() throws ResourcesNotFoundException {
        try {
            return ResponseEntity.ok(this.solicitudCalculoService.findAll());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity findById(@RequestParam(name = "id", required = true) Long id) throws ResourceNotFoundException {
        try {
            return ResponseEntity.ok(this.solicitudCalculoService.findById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping("/{requesterId}")
    public ResponseEntity create(@RequestBody SolicitudCalculoEntity entity, @PathVariable(required = true) Long requesterId) throws ResourceNotFoundException, ValidatorException  {
        try {
            return ResponseEntity.ok(this.solicitudCalculoService.calcular(entity, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

}
