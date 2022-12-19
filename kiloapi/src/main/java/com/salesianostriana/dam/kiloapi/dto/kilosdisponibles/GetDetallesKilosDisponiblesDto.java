package com.salesianostriana.dam.kiloapi.dto.kilosdisponibles;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class GetDetallesKilosDisponiblesDto {

    private Long id;
    private Long numLinea;
    private Double kgs;

}
