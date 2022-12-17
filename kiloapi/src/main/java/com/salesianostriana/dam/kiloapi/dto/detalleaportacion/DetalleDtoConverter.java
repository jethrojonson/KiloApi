package com.salesianostriana.dam.kiloapi.dto.detalleaportacion;

import com.salesianostriana.dam.kiloapi.model.Aportacion;
import com.salesianostriana.dam.kiloapi.model.DetalleAportacion;
import com.salesianostriana.dam.kiloapi.repos.DetalleAportacionRepository;
import com.salesianostriana.dam.kiloapi.service.AportacionService;
import com.salesianostriana.dam.kiloapi.service.ClaseService;
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

    public Aportacion getPostDtoToCreateDetalle(PostDetalleAportacionDto dto){

        Aportacion ap = Aportacion.builder()
                .fecha(LocalDate.now())
                .build();

        List<DetalleAportacion> auxIt = new ArrayList<>();
        dto.getTipoAlimento().forEach((aLong, aDouble) -> {

            DetalleAportacion nuevo = DetalleAportacion.builder()
                    .id(aportacionService.generateNumLinea(ap.getId(), (long) auxIt.size()+1))
                    .cantidadKilos(aDouble)
                    .tipoAlimento(tipoAlimentoService.findById(aLong).get())
                    .aportacion(ap)
                    .build();
            auxIt.add(nuevo);
        });
        aportacionService.addListDetallesToAportacion(ap, auxIt);
        aportacionService.save(ap);

        return ap;

    }

    public List<GetDetallesDto> generatelistaDetallesDto(Aportacion a){

        List<GetDetallesDto> aux = new ArrayList<>();

        a.getDetalles().forEach(d -> {
            GetDetallesDto nuevo = GetDetallesDto.builder()
                    .nombreAlimento(d.getTipoAlimento().getNombre())
                    .cantidadAlimento(d.getCantidadKilos())
                    .numLinea(d.getId().getNumLinea())
                    .build();
            aux.add(nuevo);
        });

        return aux;

    }

}
