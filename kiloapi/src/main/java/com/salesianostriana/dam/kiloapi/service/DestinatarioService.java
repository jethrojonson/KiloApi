package com.salesianostriana.dam.kiloapi.service;

import com.salesianostriana.dam.kiloapi.model.Destinatario;
import com.salesianostriana.dam.kiloapi.repos.DestinatarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DestinatarioService {

    private final DestinatarioRepository destinatarioRepository;

    public Destinatario save(Destinatario d){
        return destinatarioRepository.save(d);
    }

    public void delete(Destinatario d){
        destinatarioRepository.delete(d);
    }

    public boolean existById(Long id){
        return destinatarioRepository.existsById(id);
    }

    public Optional<Destinatario> findById(Long id){
        return destinatarioRepository.findById(id);
    }
}
