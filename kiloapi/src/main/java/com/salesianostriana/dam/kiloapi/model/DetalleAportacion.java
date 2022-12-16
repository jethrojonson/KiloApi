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

    private double cantidadKilos;

    @ManyToOne
    @MapsId("aportacion_id")
    @JoinColumn(name = "aportacion_id", foreignKey = @ForeignKey(name = "FK_DETALLEAPORTACION_APORTACION"))
    private Aportacion aportacion;

    @ManyToOne
    @JoinColumn(name = "tipo_alimento_id", foreignKey = @ForeignKey(name = "FK_DETALLEAPORTACION_TIPOALIMENTO"))
    private TipoAlimento tipoAlimento;

    ////////////////////////////////////////////
    /* HELPERS de la asociación con Aportación*/
    ////////////////////////////////////////////

    public void addToAportacion(Aportacion a) {
        this.aportacion = a;
        a.getDetalles().add(this);
    }

    public void removeFromAportacion(Aportacion a) {
        this.aportacion = null;
        a.getDetalles().remove(this);
    }

}
