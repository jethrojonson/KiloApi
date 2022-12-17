package com.salesianostriana.dam.kiloapi.service;

import com.salesianostriana.dam.kiloapi.model.Aportacion;
import com.salesianostriana.dam.kiloapi.model.KilosDisponibles;
import com.salesianostriana.dam.kiloapi.repos.KilosDisponiblesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KilosDisponiblesService {

    private final KilosDisponiblesRepository kilosDisponiblesRepository;
    private final TipoAlimentoService tipoAlimentoService;

    public List<KilosDisponibles> findAll(){
        return kilosDisponiblesRepository.findAll();
    }

    public void sumAportacionesToKilosDisponibles(Aportacion a){

        List<KilosDisponibles> lista = findAll();

        a.getDetalles().forEach(d -> {
            if(d.getTipoAlimento().getKilosDisponibles() == null){
                KilosDisponibles kd = KilosDisponibles.builder()
                        .cantidadDisponible(d.getCantidadKilos())
                        .build();
                d.getTipoAlimento().addToKilosDisponibles(kd);
                save(kd);
                tipoAlimentoService.save(d.getTipoAlimento());
            }else {

                ResponseEntity.of(
                        findById(d.getTipoAlimento().getId())
                                .map(old -> {
                                    old.setId(d.getTipoAlimento().getId());
                                    old.setTipoAlimento(d.getTipoAlimento());
                                    old.setCantidadDisponible(d.getCantidadKilos()+old.getCantidadDisponible());
                                    save(old);
                                    return Optional.of(old);
                                })
                                .orElse(Optional.empty())
                );
            }
        });

    }

    public void save(KilosDisponibles k){
        kilosDisponiblesRepository.save(k);
    }

    public void saveAll(List<KilosDisponibles> list){
        kilosDisponiblesRepository.saveAll(list);
    }

    public Optional<KilosDisponibles> findById(Long id){
        return kilosDisponiblesRepository.findById(id);
    }

}
