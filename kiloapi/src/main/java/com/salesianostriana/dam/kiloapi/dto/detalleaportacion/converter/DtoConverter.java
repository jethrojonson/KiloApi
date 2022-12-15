package com.salesianostriana.dam.kiloapi.dto.detalleaportacion.converter;

import com.salesianostriana.dam.kiloapi.dto.aportacion.GetAportacionDto;
import com.salesianostriana.dam.kiloapi.dto.detalleaportacion.GetDetalleAportacionDto;
import com.salesianostriana.dam.kiloapi.dto.detalleaportacion.PostDetalleAportacionDto;
import com.salesianostriana.dam.kiloapi.model.Aportacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DtoConverter {

    public Aportacion postDtoToAportacion (PostDetalleAportacionDto d){
        return Aportacion.builder()
                .id(d.getId())
                .fecha(d.getFecha())
                .build();
    }

    /*
    public GetDetalleAportacionDto aportacionToGetDto (Aportacion a){
        return GetDetalleAportacionDto.builder()
                .idAportacion(a.getId())
    }
     */


}
