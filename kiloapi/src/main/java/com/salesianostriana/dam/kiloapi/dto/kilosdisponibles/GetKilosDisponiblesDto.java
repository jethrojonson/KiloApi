package com.salesianostriana.dam.kiloapi.dto.kilosdisponibles;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GetKilosDisponiblesDto {

    private Long idAlimento;
    private String nombreAlimento;
    private Double kilosDisponibles;

}
