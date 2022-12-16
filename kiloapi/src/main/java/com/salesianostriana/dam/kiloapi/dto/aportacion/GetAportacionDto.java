package com.salesianostriana.dam.kiloapi.dto.aportacion;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetAportacionDto {

    private Long id;
    private LocalDate fecha;

    //private List<ListaDeParesDto> listaDePares;

    private Map<String, Double> aportaciones;

}
