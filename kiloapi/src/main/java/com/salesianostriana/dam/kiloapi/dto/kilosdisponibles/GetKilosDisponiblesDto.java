package com.salesianostriana.dam.kiloapi.dto.kilosdisponibles;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.kiloapi.views.KilosDisponiblesViews;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetKilosDisponiblesDto {

    @JsonView(KilosDisponiblesViews.Master.class)
    private Long id;

    @JsonView(KilosDisponiblesViews.Master.class)
    private String nombre;

    @JsonView(KilosDisponiblesViews.Master.class)
    private Double kilosDisponibles;

}
