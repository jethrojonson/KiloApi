package com.salesianostriana.dam.kiloapi.dto.aportacion;

import com.salesianostriana.dam.kiloapi.model.Aportacion;
import com.salesianostriana.dam.kiloapi.model.DetalleAportacion;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AportacionDtoN {

    private LocalDate fecha;
    private String nombreClase;
    private double kilosAportacion;

    public static AportacionDtoN of(Aportacion aportacion){

        double kilos = 0;

        for (DetalleAportacion d : aportacion.getDetalles()){
            kilos += d.getCantidadKilos();
        }


        return AportacionDtoN.builder()
                .fecha(aportacion.getFecha())
                .nombreClase(aportacion.getClase().getNombre())
                .kilosAportacion(kilos)
                .build();
    }

}
