package com.salesianostriana.dam.kiloapi.service;

import com.salesianostriana.dam.kiloapi.repos.AportacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AportacionService {

    private final AportacionRepository aportacionRepository;

}
