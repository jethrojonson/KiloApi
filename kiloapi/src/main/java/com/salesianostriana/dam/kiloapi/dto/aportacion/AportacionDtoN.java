package com.salesianostriana.dam.kiloapi.dto.aportacion;

import com.salesianostriana.dam.kiloapi.model.Aportacion;
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
        return AportacionDtoN.builder()
                .fecha(aportacion.getFecha())
                .nombreClase(aportacion.getClase().getNombre())
                //.kilosAportacion(aportacion.getDetalles().getKilos)
                //como obtengo los kilos, falta obtener los kilos de los detalles de esa aportación
                .build();
    }

}
