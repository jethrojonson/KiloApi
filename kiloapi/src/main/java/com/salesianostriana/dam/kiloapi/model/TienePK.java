package com.salesianostriana.dam.kiloapi.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class TienePK implements Serializable {

    private Long tipo_alimento_id;
    private Long caja_id;

}
