package com.salesianostriana.dam.kiloapi.service;

import com.salesianostriana.dam.kiloapi.dto.tipoalimento.TipoAlimentoDto;
import com.salesianostriana.dam.kiloapi.dto.tipoalimento.TipoAlimentoDtoBasicN;
import com.salesianostriana.dam.kiloapi.model.KilosDisponibles;
import com.salesianostriana.dam.kiloapi.model.TipoAlimento;
import com.salesianostriana.dam.kiloapi.repos.AportacionRepository;
import com.salesianostriana.dam.kiloapi.repos.KilosDisponiblesRepository;
import com.salesianostriana.dam.kiloapi.repos.TipoAlimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class TipoAlimentoService {

    private final TipoAlimentoRepository repo;
    private final KilosDisponiblesRepository kilosDisponiblesRepository;
    private final AportacionRepository aportacionRepository;

    public List<TipoAlimento> findAll(){ return repo.findAll(); }

    public Optional<TipoAlimento> findById(Long id) { return repo.findById(id); }

    public TipoAlimento save(TipoAlimento t) { return repo.save(t); }

    public void deleteById (Long id) { repo.deleteById(id); }

    public boolean existById (Long id) { return repo.existsById(id); }

    public TipoAlimentoDtoBasicN createTipoAlimento(TipoAlimentoDto tipoAlimentoDto) {
        TipoAlimento nuevo = TipoAlimentoDto.of(tipoAlimentoDto);
        save(nuevo);

        KilosDisponibles kilos = KilosDisponibles.builder()
                .id(nuevo.getId())
                .tipoAlimento(nuevo)
                .cantidadDisponible(0.0)
                .build();
        kilosDisponiblesRepository.save(kilos);
        TipoAlimentoDtoBasicN response = TipoAlimentoDtoBasicN.of(nuevo);
        return response;
    }

    public TipoAlimento findTipoAlimentoOnTiene(Long id){
        return repo.findTipoAlimentoOnTiene(id);
    }

    public TipoAlimento findTipoAlimentoOnDetallesAportacion(Long id){
        return  repo.findTipoAlimentoOnDetallesAportacion(id);
    }

    public boolean comprobarBorradoTipoAlimento(Long id){
        TipoAlimento t = findById(id).get();
        AtomicBoolean comprobacion = new AtomicBoolean(false);
        final double[] numero = {0.0};
        aportacionRepository.findAll().forEach(aportacion -> {
            aportacion.getDetalles().forEach(detalleAportacion -> {
                if (detalleAportacion.getTipoAlimento().equals(t)) {
                    comprobacion.set(true);
                }
            });
        });
        kilosDisponiblesRepository.findAll().forEach(kilosDisponibles -> {
            if (kilosDisponibles.getTipoAlimento().equals(t)){
                numero[0] =kilosDisponibles.getCantidadDisponible();
            }
        });
        return !comprobacion.get() && numero[0]==0.0;
    }
    
}
