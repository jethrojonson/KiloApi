package com.salesianostriana.dam.kiloapi.dto.clase;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class GetOneClaseDtoJ {

    private Long id;
    private String nombre, tutor;
    private int numAportaciones;
    private Double numTotalKilos;

    /*public static GetOneClaseDto of(Clase clase) {
        return GetOneClaseDto.builder()
                .id(clase.getId())
                .nombre(clase.getNombre())
                .tutor(clase.getTutor())
                .numAportaciones(clase.getAportaciones().size())
                //.numTotalKilos(clase.getAportaciones()) //CAMBIAR
                .build();
    }*/

}
