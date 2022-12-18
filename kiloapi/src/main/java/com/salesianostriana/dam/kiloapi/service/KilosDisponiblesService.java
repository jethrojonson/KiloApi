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

        if (lista.isEmpty()){
            a.getDetalles().forEach(d -> {
                KilosDisponibles kn = KilosDisponibles.builder()
                        .id(d.getTipoAlimento().getId())
                        .cantidadDisponible(d.getCantidadKilos())
                        .build();
                d.getTipoAlimento().addToKilosDisponibles(kn);
                kilosDisponiblesRepository.save(kn);
            });
        }else{
            lista.forEach(l -> {
                a.getDetalles().forEach(d -> {
                    if (d.getTipoAlimento() == l.getTipoAlimento()){
                        l.setCantidadDisponible(l.getCantidadDisponible()+d.getCantidadKilos());
                        kilosDisponiblesRepository.save(l);
                    }
                });
            });
        }
    }

    public KilosDisponibles save(KilosDisponibles k){
        return kilosDisponiblesRepository.save(k);
    }

    public void saveAll(List<KilosDisponibles> list){
        kilosDisponiblesRepository.saveAll(list);
    }

    public Optional<KilosDisponibles> findById(Long id){
        return kilosDisponiblesRepository.findById(id);
    }

}
