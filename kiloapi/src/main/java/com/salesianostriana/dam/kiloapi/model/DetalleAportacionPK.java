package com.salesianostriana.dam.kiloapi.model;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serial;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class DetalleAportacionPK implements Serializable {

    private Long aportacion_id;
    private Long numLinea;
}
