package com.example.capital.domain.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
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
    private Double montoInicial;

    @Basic
    @Column(name = "tasaInteres", nullable = false)
    private Double tasaInteres;

    @Basic
    @Column(name = "anios", nullable = false)
    private Short anios;

    @Basic
    @Column(name = "fechaGrabacion", nullable = false)
    private Timestamp fechaGrabacion;

    @Basic
    @Column(name = "usuarioGrabacion", nullable = false)
    private Integer usuarioGrabacion;

}