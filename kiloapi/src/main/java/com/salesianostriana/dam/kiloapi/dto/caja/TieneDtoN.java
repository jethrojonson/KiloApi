package com.salesianostriana.dam.kiloapi.dto.caja;

import com.salesianostriana.dam.kiloapi.model.Tiene;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TieneDtoN {

    private Long id;
    private String nombre;
    private double kilos;

    public static TieneDtoN of (Tiene tiene){
        return TieneDtoN.builder()
                .id(tiene.getTipoAlimento().getId())
                .nombre(tiene.getTipoAlimento().getNombre())
                .kilos(tiene.getCantidadKgs())
                .build();

    }
}
