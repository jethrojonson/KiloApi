package com.salesianostriana.dam.kiloapi.dto.kilosdisponibles;

import com.salesianostriana.dam.kiloapi.model.KilosDisponibles;
import com.salesianostriana.dam.kiloapi.service.KilosDisponiblesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class KilosDisponiblesDtoConverter {

    private final KilosDisponiblesService kilosDisponiblesService;

    public List<GetKilosDisponiblesDto> kilosDiponiblesToDto (List<KilosDisponibles> lista){

        List<GetKilosDisponiblesDto> aux = new ArrayList<>();

        lista.forEach(l -> {
            aux.add(
                    GetKilosDisponiblesDto.builder()
                            .idAlimento(l.getId())
                            .nombreAlimento(l.getTipoAlimento().getNombre())
                            .kilosDisponibles(l.getCantidadDisponible())
                            .build()
            );
        });
        kilosDisponiblesService.saveAll(lista);

        return aux;
    }

}
