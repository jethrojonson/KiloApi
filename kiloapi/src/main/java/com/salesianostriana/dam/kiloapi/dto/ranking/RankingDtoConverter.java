package com.salesianostriana.dam.kiloapi.dto.ranking;

import com.salesianostriana.dam.kiloapi.model.Clase;
import com.salesianostriana.dam.kiloapi.service.ClaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RankingDtoConverter {

    private final ClaseService claseService;

    public List<ReturnRankingDto> returnRanking (List<GetRankingQueryDto> list){

        List<ReturnRankingDto> aux = new ArrayList<>();

        list.forEach(r -> {

            Optional<Clase> c = claseService.findById(r.getId());

            final double[] sumaKg = {0};
            final int[] cantDe = {0};
            c.get().getAportaciones().forEach(a -> {
                a.getDetalles().forEach(d -> {
                    sumaKg[0] += d.getCantidadKilos();
                });
                cantDe[0] = a.getDetalles().size();
            });

            ReturnRankingDto rdto = ReturnRankingDto.builder()
                    .posicion(aux.size()+1)
                    .clase(r.getNombreClase())
                    .numeroDeAportaciones(c.get().getAportaciones().size())
                    .mediaDeKgPorAportacion(Math.round(((sumaKg[0] / cantDe[0]) / c.get().getAportaciones().size())*100.0)/100.0)
                    .kgTotalesAportados(r.getSumKgAportaciones())
                    .build();

            aux.add(rdto);
        });

        return aux;
    }

}
