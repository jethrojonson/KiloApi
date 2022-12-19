package com.salesianostriana.dam.kiloapi.dto.caja;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CajaTipoAlimentoDto {

    private Long id;
    private String nombre;
    private Double cantidadKgs;

}
