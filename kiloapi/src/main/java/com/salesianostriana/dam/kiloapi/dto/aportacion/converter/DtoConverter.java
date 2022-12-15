package com.salesianostriana.dam.kiloapi.dto.aportacion.converter;

import com.salesianostriana.dam.kiloapi.dto.aportacion.GetAportacionDto;
import com.salesianostriana.dam.kiloapi.model.Aportacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DtoConverter {

    public GetAportacionDto aportacionToGetDto (Aportacion a) {

        return GetAportacionDto.builder()
                .fecha(a.getFecha())
                .build();
    }

}
