package com.salesianostriana.dam.kiloapi.service;

import com.salesianostriana.dam.kiloapi.repos.DestinatarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DestinatarioService {

    private final DestinatarioRepository destinatarioRepository;
}
