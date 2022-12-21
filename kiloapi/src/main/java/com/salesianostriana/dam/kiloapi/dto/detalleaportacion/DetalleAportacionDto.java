package com.salesianostriana.dam.kiloapi.dto.detalleaportacion;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.kiloapi.views.KilosDisponiblesViews;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetalleAportacionDto {

    @JsonView(KilosDisponiblesViews.Details.class)
    private Long id;

    @JsonView(KilosDisponiblesViews.Details.class)
    private Long numLinea;

    @JsonView(KilosDisponiblesViews.Details.class)
    private Double kgs;

}
