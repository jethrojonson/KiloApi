package com.salesianostriana.dam.kiloapi.dto.detalleaportacion.converter;

import com.salesianostriana.dam.kiloapi.dto.detalleaportacion.PostDetalleAportacionDto;
import com.salesianostriana.dam.kiloapi.model.DetalleAportacion;
import com.salesianostriana.dam.kiloapi.model.DetalleAportacionPK;
import com.salesianostriana.dam.kiloapi.model.TipoAlimento;
import com.salesianostriana.dam.kiloapi.repos.TipoAlimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class DtoConverter {

    private final TipoAlimentoRepository tipoAlimentoRepository;

    public DetalleAportacion getPostDtoToGetDetalle(PostDetalleAportacionDto dto){

        dto.getTipoAlimento().forEach((aLong, aDouble) -> {
            DetalleAportacion detalle = new DetalleAportacion();
            detalle.setCantidadKilos(aDouble);
            detalle.setTipoAlimento(tipoAlimentoRepository.findById(aLong).get());
        });

        TipoAlimento aux = tipoAlimentoRepository.findById(dto.getId()).get();
        return DetalleAportacion.builder()
                .tipoAlimento(tipoAlimentoRepository.findById(dto.getId()).get())
                .cantidadKilos(dto.getTipoAlimento().get(dto.getId()))
                .build();
    }

}
