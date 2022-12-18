package com.salesianostriana.dam.kiloapi.dto.aportacion;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetNewAportacionDto {

    private Long id;
    private String clase;
    private LocalDate fechaAportacion;
    private List<GetDetallesDto> listadoDetalles;

}
