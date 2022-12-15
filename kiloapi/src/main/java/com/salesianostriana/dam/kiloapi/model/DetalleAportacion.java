package com.salesianostriana.dam.kiloapi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DetalleAportacion {

    @EmbeddedId
    private DetalleAportacionPK id;

    @ManyToOne(
        fetch = FetchType.EAGER
    )
    @MapsId("aportacionId")
    private Aportacion aportacion;

    private double cantidadKilos;


}
