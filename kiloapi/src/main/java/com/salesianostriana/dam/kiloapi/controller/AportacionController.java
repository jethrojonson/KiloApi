package com.salesianostriana.dam.kiloapi.controller;

import com.salesianostriana.dam.kiloapi.dto.aportacion.*;
import com.salesianostriana.dam.kiloapi.dto.detalleaportacion.DetalleDtoConverter;
import com.salesianostriana.dam.kiloapi.dto.detalleaportacion.PostDetalleAportacionDto;
import com.salesianostriana.dam.kiloapi.model.TipoAlimento;
import com.salesianostriana.dam.kiloapi.service.AportacionService;
import com.salesianostriana.dam.kiloapi.service.ClaseService;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aportacion")
@RequiredArgsConstructor
@Tag(name = "Aportaciones", description = "Controller de aportaciones")
public class AportacionController {

    private final AportacionService aportacionService;
    private final ClaseService claseService;
    private final AportacionDtoConverter aportacionDtoConverter;
    private final DetalleDtoConverter detalleDtoConverter;

    @Operation(summary = "Obtiene las aportaciones de una clase")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha obtenido el listado de aportaciones de la clase",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetAportacionByIdDto.class)
                            ,examples = @ExampleObject(
                            value = """
                                    {
                                      "idClase": 1,
                                      "listadoAportaciones": [
                                        {
                                          "id": 10,
                                          "fechaAportacion": "2022-12-17",
                                          "listadoDetalles": [
                                            {
                                              "numLinea": 1,
                                              "nombreAlimento": "Legumbres",
                                              "cantidadAlimento": 7.5
                                            },
                                            {
                                              "numLinea": 2,
                                              "nombreAlimento": "Pasta",
                                              "cantidadAlimento": 4.9
                                            }
                                          ]
                                        },
                                        {
                                          "id": 11,
                                          "fechaAportacion": "2022-12-18",
                                          "listadoDetalles": [
                                            {
                                              "numLinea": 1,
                                              "nombreAlimento": "Frutas",
                                              "cantidadAlimento": 24.8
                                            },
                                            {
                                              "numLinea": 2,
                                              "nombreAlimento": "Verduras",
                                              "cantidadAlimento": 3.3
                                            }
                                          ]
                                        }
                                      ]
                                    }
                                    """
                    ))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha podido obtener la clase",
                    content = @Content),
    })
    @GetMapping("/clase/{id}")
    public ResponseEntity<List<GetAportacionClaseDto>> findAportacionesDeUnaClase(@Parameter(description = "Id de la clase") @PathVariable Long id){

        if(!claseService.existById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(aportacionDtoConverter.sudapolla(claseService.findById(id).get()));
    }


    @Operation(summary = "Crea una aportacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado la aportación",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetNewAportacionDto.class)
                            ,examples = @ExampleObject(
                            value = """
                                    {
                                        "id": 10,
                                        "clase": "2ª DAM",
                                        "fechaAportacion": "2022-12-17",
                                        "listadoDetalles": [
                                            {
                                                "numLinea": 1,
                                                "nombreYCantidadAlimento": {
                                                    "Garbanzos": 20.0
                                                }
                                            },
                                            {
                                                "numLinea": 2,
                                                "nombreYCantidadAlimento": {
                                                    "Dodotis": 5.7
                                                }
                                            },
                                            {
                                                "numLinea": 3,
                                                "nombreYCantidadAlimento": {
                                                    "Dodotis": 5.7
                                                }
                                            }
                                        ]
                                    }
                                    """
                    ))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha podido crear la aportacion",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<GetNewAportacionDto> create(@RequestBody PostDetalleAportacionDto dto){
        if(dto.getTipoAlimento() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(aportacionDtoConverter.newAportacionDto(dto));
    }

}
