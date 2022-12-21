package com.salesianostriana.dam.kiloapi.service;

import com.salesianostriana.dam.kiloapi.dto.aportacion.GetAportacionByIdDto;
import com.salesianostriana.dam.kiloapi.dto.aportacion.GetAportacionClaseDto;
import com.salesianostriana.dam.kiloapi.dto.aportacion.GetAportacionQueryDto;
import com.salesianostriana.dam.kiloapi.dto.aportacion.PostDetalleAportacionDto;
import com.salesianostriana.dam.kiloapi.model.*;
import com.salesianostriana.dam.kiloapi.repos.AportacionRepository;
import com.salesianostriana.dam.kiloapi.repos.ClaseRepository;
import com.salesianostriana.dam.kiloapi.repos.TipoAlimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AportacionService {

    private final AportacionRepository repo;
    private final ClaseRepository claseRepo;
    private final TipoAlimentoRepository tipoRepo;
    private final KilosDisponiblesService kilosDisponiblesService;

    public List<Aportacion> findAll(){ return repo.findAll(); }

    public Optional<Aportacion> findById(Long id) { return repo.findById(id); }

    public Aportacion save(Aportacion a) { return repo.save(a); }

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

    public Map<String, Double> queryToGetACDto (Aportacion a){

        Map<String, Double> auxMap = new HashMap<>();

        a.getDetalles().forEach(d -> auxMap.put(d.getTipoAlimento().getNombre(), d.getCantidadKilos()));

        return auxMap;
    }

    public Aportacion createAportacion (PostDetalleAportacionDto dto){

        Aportacion ap = Aportacion.builder()
                .fecha(LocalDate.now())
                .build();

        Optional<Clase> clase = claseRepo.findById(dto.getClaseId());
        ap.addToClase(clase.get());

        List<DetalleAportacion> aux = new ArrayList<>();

        dto.getTipoAlimento().forEach((aLong, aDouble) -> {
            if (tipoRepo.existsById(aLong)){
                DetalleAportacion da = DetalleAportacion.builder()
                        .id(generateNumLinea(ap.getId(), (long) aux.size()+1))
                        .cantidadKilos(aDouble)
                        .tipoAlimento(tipoRepo.findById(aLong).get())
                        .build();
                ap.addDetalleAportacion(da);
                aux.add(da);
            }
        });
        kilosDisponiblesService.sumAportacionesToKilosDisponibles(ap);
        save(ap);
        return ap;
    }

    public DetalleAportacion findOneDetalleAportacion(Long idAportacion, Long numLinea) {
        return repo.findOneDetalleAportacion(idAportacion, numLinea);
    }

}
