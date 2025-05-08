package com.example.capital.domain.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "solicitud_calculos")
public class SolicitudCalculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "calculo_generator")
    @SequenceGenerator(name = "calculo_generator", sequenceName = "SEQ_CALCULOS", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "montoInicial", nullable = false)
    @Positive(message = "Monto Inicial debe ser mayor que cero")
    private Double montoInicial;

    @Basic
    @Column(name = "tasaInteres", nullable = false)
    @Positive(message = "Tasa de Interés debe ser mayor que cero")
    private Double tasaInteres;

    @Basic
    @Column(name = "anios", nullable = false)
    @Min(value = 1, message = "El número de años debe ser igual o mayor que 1")
    private Short anios;

    @Basic
    @Column(name = "fechaGrabacion", nullable = false)
    private Timestamp fechaGrabacion;

    @Basic
    @Column(name = "usuarioGrabacion", nullable = false)
    private Integer usuarioGrabacion;

}