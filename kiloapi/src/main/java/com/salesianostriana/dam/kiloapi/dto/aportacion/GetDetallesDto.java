package com.salesianostriana.dam.kiloapi.dto.aportacion;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetDetallesDto {

    private Long numLinea;
    private String alimento;
    private Double kilos;

}
