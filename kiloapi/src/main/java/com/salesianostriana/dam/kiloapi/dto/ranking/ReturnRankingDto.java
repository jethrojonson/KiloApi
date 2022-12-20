package com.salesianostriana.dam.kiloapi.dto.ranking;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReturnRankingDto {

    private int posicion;
    private String clase;
    private int numeroDeAportaciones;
    private double mediaDeKgPorAportacion;
    private double kgTotalesAportados;

}
