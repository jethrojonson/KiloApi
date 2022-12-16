package com.salesianostriana.dam.kiloapi.dto.aportacion;

import com.salesianostriana.dam.kiloapi.model.Aportacion;
import com.salesianostriana.dam.kiloapi.model.DetalleAportacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class AportacionDtoConverter {

    public GetAportacionDto getAportacionDto(Aportacion ap){

        List<DetalleAportacion> auxDetalle = ap.getDetalles();
        Map<String, Double> auxList = new HashMap<String, Double>();

        /*
        auxDetalle.forEach(d -> {
            auxList.stream()
                    .map(a -> {
                        a.setCantKg(d.getCantidadKilos());
                        a.setNombreTipoAlimento(d.getTipoAlimento().getNombre());
                        return auxList.add(a);
                    });
        });
         */

        auxDetalle.forEach(d -> {
            auxList.put(d.getTipoAlimento().getNombre() ,d.getCantidadKilos());
        });

        return GetAportacionDto.builder()
                .id(ap.getId())
                .fecha(ap.getFecha())
                .aportaciones(auxList)
                .build();
    }

}
