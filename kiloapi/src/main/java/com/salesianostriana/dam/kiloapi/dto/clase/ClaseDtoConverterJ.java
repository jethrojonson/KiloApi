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

    //JERO
    public ClaseDTOM claseToClaseDTOM(Clase c) {
        return ClaseDTOM.builder()
                .id(c.getId())
                .nombre(c.getNombre())
                .tutor(c.getTutor())
                .build();
    }

    public Clase newClaseToClase(NewClaseDTOM c) {
        return Clase.builder()
                .nombre(c.getNombre())
                .tutor(c.getTutor())
                .build();
    }

}
