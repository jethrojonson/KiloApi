package com.salesianostriana.dam.kiloapi.dto.detalleaportacion;

import lombok.*;

import java.time.LocalDate;
import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostDetalleAportacionDto {

    private Long id;
    private HashMap<String, Double> tipoAlimentoYPeso;
    private LocalDate fecha;

}
