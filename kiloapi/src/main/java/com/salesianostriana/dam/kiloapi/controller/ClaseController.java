package com.salesianostriana.dam.kiloapi.controller;

import com.salesianostriana.dam.kiloapi.dto.clase.GetOneClaseDto;
import com.salesianostriana.dam.kiloapi.model.Clase;
import com.salesianostriana.dam.kiloapi.service.ClaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clase")
@RequiredArgsConstructor
public class ClaseController {



}