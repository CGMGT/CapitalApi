package com.example.capital.infrastructure.web;

import com.example.capital.application.dto.DataTableRequestDto;
import com.example.capital.application.service.HistorialCalculoService;
import com.example.capital.domain.model.HistorialCalculoEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/historial-calculos")
public class HistorialCalculoController implements ICatalog<HistorialCalculoEntity> {

    private final HistorialCalculoService historialCalculoService;

    public HistorialCalculoController(HistorialCalculoService historialCalculoService) {
        this.historialCalculoService = historialCalculoService;
    }


    @Override
    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        try {
            return ResponseEntity.ok(this.historialCalculoService.findAll());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    @PostMapping("/findAllByPage")
    public ResponseEntity findAllByPage(@RequestBody(required = true)   DataTableRequestDto dataTableRequestDto) {
        try {
            return ResponseEntity.ok(this.historialCalculoService.findAllByPage(dataTableRequestDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    @GetMapping("/findById")
    public ResponseEntity findById(@RequestParam(name = "id", required = true) Long id) {
        try {
            return ResponseEntity.ok(this.historialCalculoService.findById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity create(@RequestBody(required = true) HistorialCalculoEntity entity, @PathVariable(required = true) Long requesterId) {
        try {
            return ResponseEntity.ok(this.historialCalculoService.create(entity, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }


}
