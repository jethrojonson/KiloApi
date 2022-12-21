package com.salesianostriana.dam.kiloapi.dto.tipoalimento;

import com.salesianostriana.dam.kiloapi.model.TipoAlimento;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TipoAlimentoDtoBasicN {

    private Long id;
    private String nombre;

    public static TipoAlimentoDtoBasicN of(TipoAlimento tipoAlimento) {
        return TipoAlimentoDtoBasicN.builder()
                .id(tipoAlimento.getId())
                .nombre(tipoAlimento.getNombre())
                .build();
    }

}
