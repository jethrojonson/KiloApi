package com.salesianostriana.dam.kiloapi.dto.aportacion;

import com.salesianostriana.dam.kiloapi.model.Aportacion;
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

    private Long claseId;
    private Map<Long, Double> tipoAlimento;

}
