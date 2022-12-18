package com.salesianostriana.dam.kiloapi.dto.tipoalimento;

import com.salesianostriana.dam.kiloapi.model.TipoAlimento;
import com.salesianostriana.dam.kiloapi.service.TipoAlimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TipoAlimentoDtoConverterN {

    private final TipoAlimentoService tipoAlimentoService;

    public TipoAlimentoDtoBasicN tipoAlimentoToTipoAlimentoDtoBasicN(TipoAlimento tipoAlimento){
        return TipoAlimentoDtoBasicN.builder()
                .id(tipoAlimento.getId())
                .nombre(tipoAlimento.getNombre())
                .build();
    }






}
