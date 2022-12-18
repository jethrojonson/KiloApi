package com.salesianostriana.dam.kiloapi.service;

import com.salesianostriana.dam.kiloapi.dto.clase.GetOneClaseDtoJ;
import com.salesianostriana.dam.kiloapi.model.Clase;
import com.salesianostriana.dam.kiloapi.ranking.GetRankingQueryDto;
import com.salesianostriana.dam.kiloapi.repos.ClaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClaseService {

    private final ClaseRepository claseRepository;

    public Optional<Clase> findById(Long id) {
        return claseRepository.findById(id);
    }

    public Clase save(Clase c) { return claseRepository.save(c); }

    public void delete(Clase clase) {
        claseRepository.delete(clase);
    }

    public void deleteById(Long id) {
        claseRepository.deleteById(id);
    }

    public Double getCantidadKilos(Long id) {
        return claseRepository.getCantidadKilos(id);
    }

    public GetOneClaseDtoJ getCntKgs(Long id) {
        return claseRepository.getCntKilos(id);
    }

    public boolean existById(Long id){
        return claseRepository.existsById(id);
    }

    public List<GetRankingQueryDto> ranking() { return claseRepository.getRankingQueryDto(); }

}
