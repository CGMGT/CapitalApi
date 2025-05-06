package com.example.capital.domain.model;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "historial_calculos")
public class HistorialCalculoEntity {
    @Id
    @Column(name = "id_solicitud", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "fecha", nullable = false)
    private OffsetDateTime fecha;

    @NotNull
    @Column(name = "interes_compuesto", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode interesCompuesto;

    @NotNull
    @Column(name = "interes_simple", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode interesSimple;

}