package com.salesianostriana.dam.kiloapi.dto.aportacion;

import lombok.*;

import java.time.LocalDate;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetAportacionClaseDto {

    private LocalDate fecha;
    private Map<String, Double> aportaciones;

}
