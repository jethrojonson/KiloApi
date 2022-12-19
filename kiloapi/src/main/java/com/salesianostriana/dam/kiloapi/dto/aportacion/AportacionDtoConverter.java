package com.salesianostriana.dam.kiloapi.dto.aportacion;

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
    private final AportacionService aportacionService;


    public GetNewAportacionDto newAportacionDto(Aportacion a){

        List<GetDetallesDto> aux = new ArrayList<>();

        a.getDetalles().forEach(d -> {
            aux.add(
                    GetDetallesDto.builder()
                            .numLinea(d.getId().getNumLinea())
                            .alimento(d.getTipoAlimento().getNombre())
                            .kilos(d.getCantidadKilos())
                            .build()
            );
        });

        return GetNewAportacionDto.builder()
                .id(a.getId())
                .clase(a.getClase().getNombre())
                .fechaAportacion(a.getFecha())
                .listadoDetalles(aux)
                .build();
    }

    public List<GetAportacionClaseDto> findAportacionesClase(Clase c){

        List<GetAportacionClaseDto> aux = new ArrayList<>();

        c.getAportaciones().forEach(a -> {
            aux.add(
                    GetAportacionClaseDto.builder()
                            .fecha(a.getFecha())
                            .aportaciones(aportacionService.queryToGetACDto(c))
                            .build()
            );
        });

        return aux;

    }

    public GetNewAportacionDto aportacionToGetNewAportacionDto(Aportacion aportacion) {

        List<GetDetallesDto> aux = new ArrayList<>();

        aportacion.getDetalles().forEach(detalleAportacion -> {
            aux.add(
                    GetDetallesDto.builder()
                            .numLinea(detalleAportacion.getId().getNumLinea())
                            .alimento(detalleAportacion.getTipoAlimento().getNombre())
                            .kilos(detalleAportacion.getCantidadKilos())
                            .build()
            );
        });

        return GetNewAportacionDto.builder()
                .id(aportacion.getId())
                .clase(aportacion.getClase().getNombre())
                .fechaAportacion(aportacion.getFecha())
                .listadoDetalles(aux)
                .build();
    }

}
