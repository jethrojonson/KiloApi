package com.salesianostriana.dam.kiloapi.service;

import com.salesianostriana.dam.kiloapi.repos.CajaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CajaService {

    private final CajaRepository cajaRepository;
}
