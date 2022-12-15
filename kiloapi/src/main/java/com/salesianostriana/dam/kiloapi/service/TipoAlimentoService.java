package com.salesianostriana.dam.kiloapi.service;

import com.salesianostriana.dam.kiloapi.repos.TipoAlimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TipoAlimentoService {

    private final TipoAlimentoRepository tipoAlimentoRepository;
}
