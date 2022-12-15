package com.salesianostriana.dam.kiloapi.dto.detalleaportacion;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetDetalleAportacionDto {

    private Long idAportacion;
    private Long numLinea;
    private String tipoAlimento;
    private double kgs;

}
