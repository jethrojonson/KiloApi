package com.salesianostriana.dam.kiloapi.service;

import com.salesianostriana.dam.kiloapi.dto.caja.CajaTipoAlimentoDto;
import com.salesianostriana.dam.kiloapi.model.*;
import com.salesianostriana.dam.kiloapi.repos.CajaRepository;
import com.salesianostriana.dam.kiloapi.repos.KilosDisponiblesRepository;
import com.salesianostriana.dam.kiloapi.repos.TieneRepository;
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

    private final TieneRepository tieneRepository;

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
        Tiene t = findTieneByIds(idC, idA);

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

        return c;

    }

    public Tiene findTieneByIds(Long idC, Long idA){

        TienePK pk = TienePK.builder()
                .caja_id(idC)
                .tipo_alimento_id(idA)
                .build();

        return tieneRepository.findById(pk).get();
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

    public List<CajaTipoAlimentoDto> findAlimentosOfACaja(Long idCaja) {
        return cajaRepository.findAlimentosOfACaja(idCaja);
    }

    public void addTipoAlimentoToCaja(Caja c, TipoAlimento ta, Double cantKg) {

        Optional<TipoAlimento> tipoAlimento = tipoAlimentoRepository.findById(ta.getId());
        Optional<Caja> caja = cajaRepository.findById(c.getId());
        TienePK idTiene = new TienePK(tipoAlimento.get().getId(), caja.get().getId());
        Optional<Tiene> tiene = tieneRepository.findById(idTiene);

        tipoAlimento.get().getKilosDisponibles().setCantidadDisponible
                (tipoAlimento.get().getKilosDisponibles().getCantidadDisponible() - cantKg);
        tipoAlimentoRepository.save(tipoAlimento.get());

        if(tiene.isEmpty()) {
            Tiene t = Tiene.builder()
                    .id(new TienePK(tipoAlimento.get().getId(), caja.get().getId()))
                    .tipoAlimento(tipoAlimento.get())
                    .caja(caja.get())
                    .cantidadKgs(cantKg)
                    .build();
            tieneRepository.save(t);
        } else{
            tiene.get().setCantidadKgs(cantKg + tiene.get().getCantidadKgs());
            tieneRepository.save(tiene.get());
        }

        caja.get().setKilosTotales(cantKg + caja.get().getKilosTotales());
        cajaRepository.save(caja.get());

    }

}
