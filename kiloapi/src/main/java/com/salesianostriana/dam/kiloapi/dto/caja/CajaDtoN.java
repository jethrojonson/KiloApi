package com.salesianostriana.dam.kiloapi.dto.caja;

import com.salesianostriana.dam.kiloapi.dto.aportacion.AportacionDtoN;
import com.salesianostriana.dam.kiloapi.model.Aportacion;
import com.salesianostriana.dam.kiloapi.model.Caja;
import com.salesianostriana.dam.kiloapi.model.Tiene;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CajaDtoN {

    private Long id;
    private String qr;
    private int numCaja;
    private List<Tiene> tiene;
    private double kilosTotales;
    //private Destinatario destinatario;

}
