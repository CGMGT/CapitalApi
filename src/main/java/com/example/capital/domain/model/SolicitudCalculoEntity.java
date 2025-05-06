package com.example.capital.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "solicitud_calculos")
public class SolicitudCalculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "montoInicial", nullable = false)
    private Double montoInicial;

    @NotNull
    @Column(name = "tasaInteres", nullable = false)
    private Double tasaInteres;

    @NotNull
    @Column(name = "anios", nullable = false)
    private Short anios;

    @NotNull
    @Column(name = "fechaGrabacion", nullable = false)
    private OffsetDateTime fechaGrabacion;

    @NotNull
    @Column(name = "usuarioGrabacion", nullable = false)
    private Integer usuarioGrabacion;

}