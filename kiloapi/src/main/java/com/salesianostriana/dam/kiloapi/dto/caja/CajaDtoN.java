package com.salesianostriana.dam.kiloapi.dto.caja;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.kiloapi.dto.aportacion.AportacionDtoN;
import com.salesianostriana.dam.kiloapi.model.Aportacion;
import com.salesianostriana.dam.kiloapi.model.Caja;
import com.salesianostriana.dam.kiloapi.model.Tiene;
import com.salesianostriana.dam.kiloapi.views.CajaViews;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CajaDtoN {

    @JsonView(CajaViews.Master.class)
    private Long id;

    @JsonView(CajaViews.Master.class)
    private String qr;

    @JsonView(CajaViews.Master.class)
    private int numCaja;

    @JsonView(CajaViews.Details.class)
    private List<Tiene> tiene;

    @JsonView(CajaViews.Master.class)
    private double kilosTotales;

    public CajaDtoN(Long id, String qr, int numCaja, double kilosTotales) {
        this.id = id;
        this.qr = qr;
        this.numCaja = numCaja;
        this.kilosTotales = kilosTotales;
    }
}
