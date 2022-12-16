package com.salesianostriana.dam.kiloapi.service;

import com.salesianostriana.dam.kiloapi.model.Aportacion;
import com.salesianostriana.dam.kiloapi.model.DetalleAportacionPK;
import com.salesianostriana.dam.kiloapi.model.TipoAlimento;
import com.salesianostriana.dam.kiloapi.repos.AportacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AportacionService {

    private final AportacionRepository repo;

    public List<Aportacion> findAll(){ return repo.findAll(); }

    public Optional<Aportacion> findById(Long id) { return repo.findById(id); }

    public void save(Aportacion a) { repo.save(a); }

    public void deleteById (Long id) { repo.deleteById(id); }

    public boolean existById (Long id) { return repo.existsById(id); }

    public DetalleAportacionPK generateNumLinea (Long idA, Long idD){
        return DetalleAportacionPK.builder()
                .aportacion_id(idA)
                .numLinea(idD)
                .build();
    }

}
