package com.salesianostriana.dam.kiloapi.dto.detalleaportacion;

import com.salesianostriana.dam.kiloapi.model.TipoAlimento;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostDetalleAportacionDto {

    private Long id;
    private HashMap<Long, Double> tipoAlimento = new HashMap<>();
    private Long numLinea;

}
