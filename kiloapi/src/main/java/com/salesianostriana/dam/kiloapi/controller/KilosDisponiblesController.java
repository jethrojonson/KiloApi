package com.salesianostriana.dam.kiloapi.controller;

import com.salesianostriana.dam.kiloapi.dto.kilosdisponibles.GetKilosDisponiblesDto;
import com.salesianostriana.dam.kiloapi.dto.kilosdisponibles.KilosDisponiblesDtoConverter;
import com.salesianostriana.dam.kiloapi.dto.tipoalimento.TipoAlimentoDtoBasicN;
import com.salesianostriana.dam.kiloapi.model.KilosDisponibles;
import com.salesianostriana.dam.kiloapi.service.KilosDisponiblesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Obtiene todos los kilos disponibles de todos los alimentos aportados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han obtenido todos kilos disponibles de los alimentos aportados",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetKilosDisponiblesDto.class)
                            , examples = @ExampleObject(
                            value = """
                                    [
                                      {
                                        "idAlimento": 3,
                                        "nombreAlimento": "Legumbres",
                                        "kilosDisponibles": 84.32
                                      },
                                      {
                                        "idAlimento": 4,
                                        "nombreAlimento": "Frutos secos",
                                        "kilosDisponibles": 33.6
                                      }
                                    ]
                                    """
                    ))}),
            @ApiResponse(responseCode = "404",
                    description = "No hay existencias de kilos disponibles",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<List<GetKilosDisponiblesDto>> findAll(){

        if(kilosDisponiblesService.findAll().isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();


        return ResponseEntity.ok(kilosDisponiblesDtoConverter.kilosDiponiblesToDto(kilosDisponiblesService.findAll()));

    }

}
