package com.salesianostriana.dam.kiloapi.dto.detalleaportacion.converter;

import com.salesianostriana.dam.kiloapi.dto.detalleaportacion.PostDetalleAportacionDto;
import com.salesianostriana.dam.kiloapi.model.DetalleAportacion;
import com.salesianostriana.dam.kiloapi.service.AportacionService;
import com.salesianostriana.dam.kiloapi.service.TipoAlimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DetalleDtoConverter {

    private final TipoAlimentoService tipoAlimentoService;
    private final AportacionService aportacionService;

    public void getPostDtoToCreateDetalle(PostDetalleAportacionDto dto){

        List<DetalleAportacion> auxIt = new ArrayList<>();

        dto.getTipoAlimento().forEach((aLong, aDouble) -> {
            DetalleAportacion nuevo = DetalleAportacion.builder()
                    .cantidadKilos(aDouble)
                    .tipoAlimento(tipoAlimentoService.findById(aLong).get())
                    .id(aportacionService.generateNumLinea(dto.getId(), (long) auxIt.size()+1))
                    .build();
            auxIt.add(nuevo);
        });
    }



}
