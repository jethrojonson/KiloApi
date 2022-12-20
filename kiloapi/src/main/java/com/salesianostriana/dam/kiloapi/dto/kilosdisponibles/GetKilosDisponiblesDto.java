package com.salesianostriana.dam.kiloapi.dto.kilosdisponibles;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class GetKilosDisponiblesDto {

    private Long idAlimento;
    private String nombreAlimento;
    private Double kilosDisponibles;

}
