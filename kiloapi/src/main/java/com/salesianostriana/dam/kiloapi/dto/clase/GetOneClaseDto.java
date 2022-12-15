package com.salesianostriana.dam.kiloapi.dto.clase;

import com.salesianostriana.dam.kiloapi.model.Clase;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class GetOneClaseDto {

    private Long id;
    private String nombre, tutor;
    private int numAportaciones, numTotalKilos;

    public static GetOneClaseDto of(Clase clase) {
        return GetOneClaseDto.builder()
                .id(clase.getId())
                .nombre(clase.getNombre())
                .tutor(clase.getTutor())
                .numAportaciones(clase.getAportaciones().size())
                .numTotalKilos(clase.getAportaciones().size()) //CAMBIAR
                .build();
    }

}
