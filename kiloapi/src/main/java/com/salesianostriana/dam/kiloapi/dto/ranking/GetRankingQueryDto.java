package com.salesianostriana.dam.kiloapi.dto.ranking;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetRankingQueryDto {

    private Long id;
    private String nombreClase;
    private Long cantidadDeAportaciones;

}
