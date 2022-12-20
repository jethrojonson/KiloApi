package com.salesianostriana.dam.kiloapi.dto.kilosdisponibles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@SuperBuilder
public class GetOneKilosDisponiblesDto extends GetKilosDisponiblesDto{

    private List<GetDetallesKilosDisponiblesDto> aportaciones;

}
