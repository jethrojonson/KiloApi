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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DetalleDtoConverter {

    private final TipoAlimentoService tipoAlimentoService;
    private final AportacionService aportacionService;

    private final ClaseService claseService;

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

        //aportacionService.addAportacionToClase(ap, claseService.findById(1L).get());

        return ap;

    }

    public List<GetDetallesDto> generatelistaDetallesDto(Aportacion a){

        List<GetDetallesDto> aux = new ArrayList<>();
        Map<String, Double> mapAux = new HashMap<>();

        a.getDetalles().forEach(d -> {
            mapAux.put(d.getTipoAlimento().getNombre(), d.getCantidadKilos());
            GetDetallesDto nuevo = new GetDetallesDto();
            nuevo.setNumLinea(d.getId().getNumLinea());
            mapAux.forEach((nombre, kilos)->{
                nuevo.setNombreYCantidadAlimento(Map.of(nombre, kilos));
            });
            aux.add(nuevo);
        });

        return aux;

    }

}
