package com.salesianostriana.dam.kiloapi.ranking;

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
