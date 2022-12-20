package com.salesianostriana.dam.kiloapi.dto.caja;

import com.salesianostriana.dam.kiloapi.model.Caja;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CajaDtoPut {

    private Long id;
    private String qr;
    private int numCaja;
    private double kilosTotales;
    private List<CajaTipoAlimentoDto> listaAlimentos;
}
