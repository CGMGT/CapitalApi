package com.example.capital.infrastructure.web;

import com.example.capital.application.service.SolicitudCalculoService;
import com.example.capital.domain.model.SolicitudCalculoEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/calcular-interes")
public class SolicitudCalculoController {

    private final SolicitudCalculoService solicitudCalculoService;

    public SolicitudCalculoController(SolicitudCalculoService solicitudCalculoService) {
        this.solicitudCalculoService = solicitudCalculoService;
    }

    @GetMapping
    public List<SolicitudCalculoEntity> getAllSolicitudCalculo() {
        return solicitudCalculoService.getAllSolicitudCalculo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudCalculoEntity> getSolicitudCalculoById(@PathVariable Long id) {
        Optional<SolicitudCalculoEntity> product = solicitudCalculoService.getSolicitudCalculoById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SolicitudCalculoEntity> createSolicitudCalculo(@RequestBody SolicitudCalculoEntity solicitudCalculoEntity) {
        SolicitudCalculoEntity savedSolicitudCalculoEntity = solicitudCalculoService.addSolicitudCalculo(solicitudCalculoEntity);
        return ResponseEntity.ok(savedSolicitudCalculoEntity);
    }
}
