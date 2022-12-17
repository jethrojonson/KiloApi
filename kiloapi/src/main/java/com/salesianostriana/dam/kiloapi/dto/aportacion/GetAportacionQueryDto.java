package com.salesianostriana.dam.kiloapi.dto.aportacion;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetAportacionQueryDto {

    private String nombre;
    private Double cantidad;

}
