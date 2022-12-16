package com.salesianostriana.dam.kiloapi.dto.clase;

import com.salesianostriana.dam.kiloapi.model.Clase;
import com.salesianostriana.dam.kiloapi.service.ClaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClaseDtoConverterJ {

    private final ClaseService claseService;

    public GetOneClaseDtoJ claseToGetOneClaseDto(Clase clase) {
        return GetOneClaseDtoJ.builder()
                .id(clase.getId())
                .nombre(clase.getNombre())
                .tutor(clase.getTutor())
                .numAportaciones(clase.getAportaciones().size())
                .numTotalKilos(claseService.getCantidadKilos(clase.getId()))
                .build();
    }

}
