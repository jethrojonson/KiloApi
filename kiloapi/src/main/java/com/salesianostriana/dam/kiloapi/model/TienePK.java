package com.salesianostriana.dam.kiloapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class TienePK implements Serializable {

    private Long tipo_alimento_id;
    private Long caja_id;

}
