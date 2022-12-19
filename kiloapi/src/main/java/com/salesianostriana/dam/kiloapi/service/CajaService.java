package com.salesianostriana.dam.kiloapi.service;

import com.salesianostriana.dam.kiloapi.model.Caja;
import com.salesianostriana.dam.kiloapi.model.KilosDisponibles;
import com.salesianostriana.dam.kiloapi.model.TipoAlimento;
import com.salesianostriana.dam.kiloapi.repos.CajaRepository;
import com.salesianostriana.dam.kiloapi.repos.TipoAlimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CajaService {

    private final CajaRepository cajaRepository;

    private final CajaRepository repo;

    public List<Caja> findAll(){
        return cajaRepository.findAll();
    }

    public Optional<Caja> findById(Long id) { return repo.findById(id); }

    public Caja save(Caja c) { return repo.save(c); }

    public void deleteById (Long id) { repo.deleteById(id); }

    public boolean existById (Long id) { return repo.existsById(id); }

    public Caja changeTipoAlimentoAmount (Caja c, TipoAlimento ta, Double kgs){

        c.getTieneList().forEach(t -> {
            if(Objects.equals(t.getTipoAlimento().getId(), ta.getId())){
                t.setCantidadKgs(kgs);
                repo.save(c);
            }
        });

        return c;

    }

}
