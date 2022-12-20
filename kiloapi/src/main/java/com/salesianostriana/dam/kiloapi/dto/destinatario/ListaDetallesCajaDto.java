package com.salesianostriana.dam.kiloapi.dto.destinatario;

import lombok.*;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class ListaDetallesCajaDto {

    private int numCaja;
    private Double kgsTotales;
    private List<ListaTipoAlimentoDto> alimentos;

    public ListaDetallesCajaDto(int numCaja, Double kgsTotales) {
        this.numCaja = numCaja;
        this.kgsTotales = kgsTotales;
    }
}
