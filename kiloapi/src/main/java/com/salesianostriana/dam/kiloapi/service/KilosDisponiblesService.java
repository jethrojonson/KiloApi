package com.salesianostriana.dam.kiloapi.service;

import com.salesianostriana.dam.kiloapi.dto.detalleaportacion.DetalleAportacionDto;
import com.salesianostriana.dam.kiloapi.model.*;
import com.salesianostriana.dam.kiloapi.repos.AportacionRepository;
import com.salesianostriana.dam.kiloapi.repos.KilosDisponiblesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KilosDisponiblesService {

    private final KilosDisponiblesRepository kilosDisponiblesRepository;
    private final AportacionRepository aportacionRepository;

    public List<KilosDisponibles> findAll() {
        return kilosDisponiblesRepository.findAll();
    }

    public void sumAportacionesToKilosDisponibles(Aportacion a) {

        List<KilosDisponibles> lista = findAll();
        List<Long> auxLong = new ArrayList<>();

        lista.forEach(l-> {
            auxLong.add(l.getTipoAlimento().getId());
        });

        if (lista.isEmpty()) {
            a.getDetalles().forEach(d -> {
                KilosDisponibles kn = KilosDisponibles.builder()
                        .id(d.getTipoAlimento().getId())
                        .cantidadDisponible(d.getCantidadKilos())
                        .build();
                d.getTipoAlimento().addToKilosDisponibles(kn);
                kilosDisponiblesRepository.save(kn);
            });
        } else {
            lista.forEach(l -> {
                a.getDetalles().forEach(d -> {
                    if (!auxLong.contains(d.getTipoAlimento().getId())){
                        KilosDisponibles kn = KilosDisponibles.builder()
                                .id(d.getTipoAlimento().getId())
                                .cantidadDisponible(d.getCantidadKilos())
                                .build();
                        d.getTipoAlimento().addToKilosDisponibles(kn);
                        kilosDisponiblesRepository.save(kn);
                    }else if (d.getTipoAlimento() == l.getTipoAlimento()) {
                        l.setCantidadDisponible(l.getCantidadDisponible() + d.getCantidadKilos());
                        kilosDisponiblesRepository.save(l);
                    }
                });
            });
        }
    }

    public KilosDisponibles save(KilosDisponibles k) {
        return kilosDisponiblesRepository.save(k);
    }

    public void saveAll(List<KilosDisponibles> list) {
        kilosDisponiblesRepository.saveAll(list);
    }

    public Optional<KilosDisponibles> findById(Long id) {
        return kilosDisponiblesRepository.findById(id);
    }

    public void editOneKiloDisponible(DetalleAportacion da) {
        Optional<KilosDisponibles> edit = kilosDisponiblesRepository.findById(da.getTipoAlimento().getId());
        aportacionRepository.save(da.getAportacion());
        edit.get().setCantidadDisponible(aportacionRepository.findAllKilosOfATipoAlimento(da.getTipoAlimento().getId()));
        kilosDisponiblesRepository.save(edit.get());
    }

    public void removeKilosDisponiblesOfADetalleAportacion(DetalleAportacion da) {
        Optional<KilosDisponibles> delete = kilosDisponiblesRepository.findById(da.getTipoAlimento().getId());
        delete.get().setCantidadDisponible(delete.get().getCantidadDisponible() < da.getCantidadKilos()
                ? 0 : delete.get().getCantidadDisponible() - da.getCantidadKilos());
        kilosDisponiblesRepository.save(delete.get());
    }

    public void removeKilosDisponiblesOfAnAportacion(Aportacion a) {
        List<KilosDisponibles> lista = kilosDisponiblesRepository.findAll();

        lista.forEach(kilosDisponibles -> {
            a.getDetalles().forEach(detalleAportacion -> {
                if (detalleAportacion.getTipoAlimento() == kilosDisponibles.getTipoAlimento()) {
                    kilosDisponibles.setCantidadDisponible(kilosDisponibles.getCantidadDisponible() - detalleAportacion.getCantidadKilos());
                    kilosDisponiblesRepository.save(kilosDisponibles);
                }
            });
        });
    }

    public List<DetalleAportacionDto> findDetallesOfKiloDisponible(Long idTipoAlimento) {
        return kilosDisponiblesRepository.findDetallesOfKiloDisponible(idTipoAlimento);
    }

}
