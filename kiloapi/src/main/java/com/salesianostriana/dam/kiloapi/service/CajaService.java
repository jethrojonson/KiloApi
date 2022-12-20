package com.salesianostriana.dam.kiloapi.service;

import com.salesianostriana.dam.kiloapi.model.*;
import com.salesianostriana.dam.kiloapi.repos.CajaRepository;
import com.salesianostriana.dam.kiloapi.repos.KilosDisponiblesRepository;
import com.salesianostriana.dam.kiloapi.repos.TipoAlimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CajaService {

    private final CajaRepository cajaRepository;
    private final KilosDisponiblesRepository kilosDisponiblesRepository;
    private final TipoAlimentoRepository tipoAlimentoRepository;

    private final CajaRepository repo;

    public List<Caja> findAll(){
        return cajaRepository.findAll();
    }

    public Optional<Caja> findById(Long id) { return repo.findById(id); }

    public Caja save(Caja c) { return repo.save(c); }

    public void deleteById(Long id) { repo.deleteById(id); }
    public boolean existById (Long id) { return repo.existsById(id); }

    public Caja changeTipoAlimentoAmount (Long idC, Long idA, Double kgs){

        Caja c = cajaRepository.findById(idC).get();
        TipoAlimento ta = tipoAlimentoRepository.findById(idA).get();

        c.getTieneList().forEach(t -> {
            if(t.getTipoAlimento().equals(ta) && kgs < t.getTipoAlimento().getKilosDisponibles().getCantidadDisponible()){
                ResponseEntity.of(
                        kilosDisponiblesRepository.findById(ta.getId())
                                .map(old -> {
                                    old.setTipoAlimento(t.getTipoAlimento());
                                    old.setId(t.getTipoAlimento().getId());
                                    old.setCantidadDisponible(old.getCantidadDisponible() + t.getCantidadKgs() - kgs);
                                    return kilosDisponiblesRepository.save(old);
                                })
                );
                c.setKilosTotales(c.getKilosTotales() + kgs - t.getCantidadKgs());
                t.setCantidadKgs(kgs);
                repo.save(c);
            }
        });

        return c;

    }

    public Long preRemoveAlimentos (Long id) {

        Caja caja = cajaRepository.findById(id).get();

        caja.getTieneList().forEach(tiene -> {
            kilosDisponiblesRepository.findAll().forEach(k -> {
                if(tiene.getTipoAlimento().equals(k.getTipoAlimento())){
                    k.setCantidadDisponible(k.getCantidadDisponible()+tiene.getCantidadKgs());
                    kilosDisponiblesRepository.save(k);
                }
            });
        });

        return caja.getId();
    }

}
