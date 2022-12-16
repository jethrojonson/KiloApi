package com.salesianostriana.dam.kiloapi.dto.detalleaportacion;

import com.salesianostriana.dam.kiloapi.model.Aportacion;
import com.salesianostriana.dam.kiloapi.model.DetalleAportacion;
import com.salesianostriana.dam.kiloapi.repos.DetalleAportacionRepository;
import com.salesianostriana.dam.kiloapi.service.AportacionService;
import com.salesianostriana.dam.kiloapi.service.TipoAlimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DetalleDtoConverter {

    private final TipoAlimentoService tipoAlimentoService;
    private final AportacionService aportacionService;
    private final DetalleAportacion helpers;

    public void getPostDtoToCreateDetalle(PostDetalleAportacionDto dto){

        /*
        List<DetalleAportacion> auxIt = new ArrayList<>();
        dto.getTipoAlimento().forEach((aLong, aDouble) -> {

            Aportacion ap = Aportacion.builder()
                    .fecha(LocalDate.now())
                    .build();

            DetalleAportacion nuevo = DetalleAportacion.builder()
                    .id(aportacionService.generateNumLinea(ap.getId(), (long) auxIt.size()+1))
                    .cantidadKilos(aDouble)
                    .tipoAlimento(tipoAlimentoService.findById(aLong).get())
                    .aportacion(ap)
                    .build();
            auxIt.add(nuevo);
        });
         */

    }

}
