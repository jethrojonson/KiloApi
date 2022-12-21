package com.salesianostriana.dam.kiloapi.dto.kilosdisponibles;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.kiloapi.dto.detalleaportacion.DetalleAportacionDto;
import com.salesianostriana.dam.kiloapi.views.KilosDisponiblesViews;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetKilosDisponiblesDto {

    @JsonView(KilosDisponiblesViews.Master.class)
    private Long id;

    @JsonView(KilosDisponiblesViews.Master.class)
    private String nombre;

    @JsonView(KilosDisponiblesViews.Master.class)
    private Double kilosDisponibles;

    @JsonView(KilosDisponiblesViews.Details.class)
    private List<DetalleAportacionDto> aportaciones;


}
