package com.salesianostriana.dam.kiloapi.dto.caja;

import com.salesianostriana.dam.kiloapi.dto.aportacion.AportacionDtoN;
import com.salesianostriana.dam.kiloapi.model.Aportacion;
import com.salesianostriana.dam.kiloapi.model.Caja;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CajaDtoN {

    private String qr;
    private int numCaja;
    private double kilosTotales;
    //private Destinatario destinatario;

}
