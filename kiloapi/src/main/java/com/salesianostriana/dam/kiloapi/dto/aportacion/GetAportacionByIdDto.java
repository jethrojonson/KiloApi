package com.salesianostriana.dam.kiloapi.dto.aportacion;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetAportacionByIdDto {

    private Long idClase;
    private List<GetNewAportacionDto> listadoAportaciones;

}
