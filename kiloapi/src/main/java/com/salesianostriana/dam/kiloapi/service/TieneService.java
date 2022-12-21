package com.salesianostriana.dam.kiloapi.service;

import com.salesianostriana.dam.kiloapi.model.Tiene;
import com.salesianostriana.dam.kiloapi.repos.TieneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TieneService {

    private final TieneRepository repository;

    public void remove(Tiene t){
        repository.delete(t);
    }

    public Tiene findById(Long cajaId,Long tipoId){
        return repository.findOneTiene(cajaId,tipoId);
    }

}
