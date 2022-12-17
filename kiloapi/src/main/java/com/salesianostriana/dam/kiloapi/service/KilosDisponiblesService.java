package com.salesianostriana.dam.kiloapi.service;

import com.salesianostriana.dam.kiloapi.model.Aportacion;
import com.salesianostriana.dam.kiloapi.model.KilosDisponibles;
import com.salesianostriana.dam.kiloapi.repos.KilosDisponiblesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KilosDisponiblesService {

    private final KilosDisponiblesRepository kilosDisponiblesRepository;

    public List<KilosDisponibles> findAll(){
        return kilosDisponiblesRepository.findAll();
    }

    public void sumAportacionesToKilosDisponibles(Aportacion a){

        a.getDetalles().forEach(d -> {
            kilosDisponiblesRepository.findAll().forEach(k -> {
                if (!Objects.equals(d.getTipoAlimento().getId(), k.getId())){
                    KilosDisponibles kd = KilosDisponibles.builder()
                            .id(d.getTipoAlimento().getId())
                            .cantidadDisponible(d.getCantidadKilos())
                            .build();
                    d.getTipoAlimento().addToKilosDisponibles(kd);
                    save(kd);
                }else{
                    KilosDisponibles kd = findById(d.getTipoAlimento().getId()).get();
                    kd.setCantidadDisponible(k.getCantidadDisponible()+d.getCantidadKilos());
                    save(kd);
                }
            });
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
