package com.salesianostriana.dam.kiloapi.service;

import com.salesianostriana.dam.kiloapi.dto.destinatario.CreateDestinatarioDto;
import com.salesianostriana.dam.kiloapi.dto.destinatario.GetDestinatarioDto;
import com.salesianostriana.dam.kiloapi.dto.destinatario.ListaDetallesCajaDto;
import com.salesianostriana.dam.kiloapi.dto.destinatario.ListaTipoAlimentoDto;
import com.salesianostriana.dam.kiloapi.model.Destinatario;
import com.salesianostriana.dam.kiloapi.repos.DestinatarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public GetDestinatarioDto createDestinatario(CreateDestinatarioDto createDestinatarioDto) {
        Destinatario newDestinatario = CreateDestinatarioDto.of(createDestinatarioDto);
        save(newDestinatario);
        GetDestinatarioDto response = GetDestinatarioDto.of(newDestinatario);
        return response;
    }

    public List<ListaTipoAlimentoDto> findNombreTipoAlimentoYCantidadKgsDeUnDestinatario(Long idDestinatario) {
        return destinatarioRepository.findNombreTipoAlimentoYCantidadKgsDeUnDestinatario(idDestinatario);
    }

    public List<ListaDetallesCajaDto> findNumCajaYKgsTotalesDeUnDestinatario(Long idDestinatario) {
        return destinatarioRepository.findNumCajaYKgsTotalesDeUnDestinatario(idDestinatario);
    }

    public List<ListaDetallesCajaDto> getListaDetallesCajaDto(Destinatario destinatario) {
        List<ListaDetallesCajaDto> aux = findNumCajaYKgsTotalesDeUnDestinatario(destinatario.getId());
        aux.forEach(listaDetallesCajaDto -> {
            listaDetallesCajaDto.setAlimentos(findNombreTipoAlimentoYCantidadKgsDeUnDestinatario(destinatario.getId()));
        });
        return aux;
    }
}
