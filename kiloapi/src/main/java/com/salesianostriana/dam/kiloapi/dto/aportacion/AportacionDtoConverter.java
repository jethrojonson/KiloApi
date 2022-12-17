package com.salesianostriana.dam.kiloapi.dto.aportacion;

import com.salesianostriana.dam.kiloapi.dto.detalleaportacion.DetalleDtoConverter;
import com.salesianostriana.dam.kiloapi.dto.detalleaportacion.PostDetalleAportacionDto;
import com.salesianostriana.dam.kiloapi.model.Aportacion;
import com.salesianostriana.dam.kiloapi.model.Clase;
import com.salesianostriana.dam.kiloapi.model.DetalleAportacion;
import com.salesianostriana.dam.kiloapi.service.AportacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class AportacionDtoConverter {

    private final DetalleDtoConverter detalleDtoConverter;
    private final AportacionService aportacionService;

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



    public GetNewAportacionDto newAportacionDto(PostDetalleAportacionDto dto){
        Aportacion a = detalleDtoConverter.getPostDtoToCreateDetalle(dto);

        return GetNewAportacionDto.builder()
                .id(a.getId())
                .clase(a.getClase().getNombre())
                .fechaAportacion(a.getFecha())
                .listadoDetalles(detalleDtoConverter.generatelistaDetallesDto(a))
                .build();
    }

    public List<GetNewAportacionDto> generateListGetAportaciones(Clase c){

        List<GetNewAportacionDto> aux = new ArrayList<>();

        c.getAportaciones().forEach(a -> {
            aux.add(GetNewAportacionDto.builder()
                    .id(a.getId())
                    .clase(a.getClase().getNombre())
                    .fechaAportacion(a.getFecha())
                    .listadoDetalles(detalleDtoConverter.generatelistaDetallesDto(a))
                    .build());
        });

        return aux;
    }


}
