package com.salesianostriana.dam.kiloapi.dto.detalleaportacion;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetDetallesDto {

    private Long numLinea;
    //private String nombreAlimento;
    //private Double cantidadAlimento;
    private Map<String, Double> nombreYCantidadAlimento = new HashMap<>();

}
