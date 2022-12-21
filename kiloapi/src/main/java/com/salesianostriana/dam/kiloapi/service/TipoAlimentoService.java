package com.salesianostriana.dam.kiloapi.service;

import com.salesianostriana.dam.kiloapi.dto.tipoalimento.TipoAlimentoDto;
import com.salesianostriana.dam.kiloapi.dto.tipoalimento.TipoAlimentoDtoBasicN;
import com.salesianostriana.dam.kiloapi.model.KilosDisponibles;
import com.salesianostriana.dam.kiloapi.model.TipoAlimento;
import com.salesianostriana.dam.kiloapi.repos.KilosDisponiblesRepository;
import com.salesianostriana.dam.kiloapi.repos.TipoAlimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TipoAlimentoService {

    private final TipoAlimentoRepository repo;
    private final KilosDisponiblesRepository kilosDisponiblesRepository;

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
    
}
