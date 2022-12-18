package com.salesianostriana.dam.kiloapi.controller;

import com.salesianostriana.dam.kiloapi.dto.kilosdisponibles.GetKilosDisponiblesDto;
import com.salesianostriana.dam.kiloapi.dto.kilosdisponibles.KilosDisponiblesDtoConverter;
import com.salesianostriana.dam.kiloapi.model.KilosDisponibles;
import com.salesianostriana.dam.kiloapi.service.KilosDisponiblesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kilosDisponibles")
@RequiredArgsConstructor
@Tag(name = "Kilos disponibles", description = "Controller de los kilos disponibles")
public class KilosDisponiblesController {

    private final KilosDisponiblesService kilosDisponiblesService;
    private final KilosDisponiblesDtoConverter kilosDisponiblesDtoConverter;

    @GetMapping("/")
    public ResponseEntity<List<GetKilosDisponiblesDto>> findAll(){

        if(kilosDisponiblesService.findAll().isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();


        return ResponseEntity.ok(kilosDisponiblesDtoConverter.kilosDiponiblesToDto(kilosDisponiblesService.findAll()));

    }

}
