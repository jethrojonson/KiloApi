package com.salesianostriana.dam.kiloapi.dto.tipoalimento;

import com.salesianostriana.dam.kiloapi.model.TipoAlimento;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class TipoAlimentoDto {

    private String nombre;

    public static TipoAlimento of(TipoAlimentoDto tipoAlimentoDto) {
        return TipoAlimento.builder()
                .nombre(tipoAlimentoDto.getNombre())
                .build();
    }

}
