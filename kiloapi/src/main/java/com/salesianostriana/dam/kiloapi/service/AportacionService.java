package com.salesianostriana.dam.kiloapi.service;

import com.salesianostriana.dam.kiloapi.dto.aportacion.GetAportacionByIdDto;
import com.salesianostriana.dam.kiloapi.dto.aportacion.GetAportacionClaseDto;
import com.salesianostriana.dam.kiloapi.dto.aportacion.GetAportacionQueryDto;
import com.salesianostriana.dam.kiloapi.model.*;
import com.salesianostriana.dam.kiloapi.repos.AportacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public void addListDetallesToAportacion(Aportacion a, List<DetalleAportacion> d){
        a.getDetalles().addAll(d);
        save(a);
    }

    public void addAportacionToClase(Aportacion a, Clase c){
        a.addToClase(c);
        save(a);
    }


    public Aportacion findLastOneCreated(){
        return repo.findFirstByOrderByIdDesc();
    }

    public Map<String, Double> queryToGetACDto (Clase c){

        List<GetAportacionQueryDto> auxList = repo.getListOfNamesAmount(c.getId());
        Map<String, Double> auxMap = new HashMap<>();


        auxList.forEach(a -> {
            auxMap.put(a.getNombre(), a.getCantidad());
        });

        return auxMap;
    }

}
