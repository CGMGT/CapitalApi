package com.example.capital.domain.model;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "historial_calculos")
public class HistorialCalculoEntity {
    @Id
    @Column(name = "id_solicitud", nullable = false)
    private Long id;

    @Basic
    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;

    @Basic
    @Column(name = "interes_compuesto", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode interesCompuesto;

    @Basic
    @Column(name = "interes_simple", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode interesSimple;

}