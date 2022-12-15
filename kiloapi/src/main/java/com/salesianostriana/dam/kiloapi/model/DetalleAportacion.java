package com.salesianostriana.dam.kiloapi.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DetalleAportacion {


    @EmbeddedId
    private DetalleAportacionPK id;

    @ManyToOne
    @JoinColumn(name = "detalle_aportacion", foreignKey = @ForeignKey(name = "PK_DETALLE_APORTACION"))
    @MapsId("aportacionId")
    private Aportacion aportacion;

    private double cantidadKilos;


    @ManyToOne
    @JoinColumn(name = "tipo_alimento_id", foreignKey = @ForeignKey(name = "FK_DETALLE_TIPOALIMENTO"))
    private TipoAlimento tipoAlimento;

}
