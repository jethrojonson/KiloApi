package com.salesianostriana.dam.kiloapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.kiloapi.dto.kilosdisponibles.GetKilosDisponiblesDto;
import com.salesianostriana.dam.kiloapi.dto.kilosdisponibles.KilosDisponiblesDtoConverter;
import com.salesianostriana.dam.kiloapi.model.KilosDisponibles;
import com.salesianostriana.dam.kiloapi.service.KilosDisponiblesService;
import com.salesianostriana.dam.kiloapi.views.KilosDisponiblesViews;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
    @JsonView(KilosDisponiblesViews.Master.class)
    public ResponseEntity<List<GetKilosDisponiblesDto>> findAll(){

        if(kilosDisponiblesService.findAll().isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();


        return ResponseEntity.ok(kilosDisponiblesDtoConverter.kilosDiponiblesToDto(kilosDisponiblesService.findAll()));

    }

    @Operation(summary = "Obtiene los Kilos Disponibles de un tipo de alimento en concreto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado con éxito los Kilos Disponibles del tipo de alimento pasado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetKilosDisponiblesDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "idAlimento": 3,
                                                "nombreAlimento": "Garbanzos",
                                                "kilosDisponibles": 6.6,
                                                "aportaciones": [
                                                    {
                                                        "id": 10,
                                                        "numLinea": 1,
                                                        "kgs": 3.3
                                                    },
                                                    {
                                                        "id": 11,
                                                        "numLinea": 1,
                                                        "kgs": 3.3
                                                    }
                                                ]
                                            }
                                            """
                            )})}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado los kilos disponibles del tipo de alimento indicado",
                    content = @Content)
    })
    @GetMapping("/{idTipoAlimento}")
    public ResponseEntity<GetKilosDisponiblesDto> getOneKilosDisponibles(@Parameter(description = "Identificador del tipo de alimento para buscar sus kilos disponibles")
                                                                                @PathVariable Long idTipoAlimento) {
        Optional<KilosDisponibles> kilosDisponibles = kilosDisponiblesService.findById(idTipoAlimento);
        if(kilosDisponibles.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(kilosDisponiblesDtoConverter.kilosDisponiblesToGetOneKilosDisponibles(kilosDisponibles.get()));
    }

}
