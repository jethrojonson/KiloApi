package com.salesianostriana.dam.kiloapi.model;

import lombok.NoArgsConstructor;
import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class DetalleAportacionPK implements Serializable {

    private Long aportacion_id;
    private Long numLinea;
}
