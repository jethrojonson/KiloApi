package com.salesianostriana.dam.kiloapi.service;

import com.salesianostriana.dam.kiloapi.model.TipoAlimento;
import com.salesianostriana.dam.kiloapi.repos.TipoAlimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TipoAlimentoService {

    private final TipoAlimentoRepository repo;

    public List<TipoAlimento> findAll(){ return repo.findAll(); }

    public Optional<TipoAlimento> findById(Long id) { return repo.findById(id); }

    public void save(TipoAlimento t) { repo.save(t); }

    public void deleteById (Long id) { repo.deleteById(id); }

    public boolean existById (Long id) { return repo.existsById(id); }
    
}
