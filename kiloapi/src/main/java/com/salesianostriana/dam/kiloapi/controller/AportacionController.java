package com.salesianostriana.dam.kiloapi.controller;

import com.salesianostriana.dam.kiloapi.dto.aportacion.*;
import com.salesianostriana.dam.kiloapi.dto.aportacion.PostDetalleAportacionDto;
import com.salesianostriana.dam.kiloapi.model.Aportacion;
import com.salesianostriana.dam.kiloapi.model.Clase;
import com.salesianostriana.dam.kiloapi.model.DetalleAportacion;
import com.salesianostriana.dam.kiloapi.service.AportacionService;
import com.salesianostriana.dam.kiloapi.service.ClaseService;
import com.salesianostriana.dam.kiloapi.service.KilosDisponiblesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/aportacion")
@RequiredArgsConstructor
@Tag(name = "Aportaciones", description = "Controller de aportaciones")
public class AportacionController {

    private final AportacionService aportacionService;
    private final ClaseService claseService;
    private final AportacionDtoConverter aportacionDtoConverter;
    private final KilosDisponiblesService kilosDisponiblesService;


    @Operation(summary = "Obtiene las aportaciones de una clase")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha obtenido el listado de aportaciones de la clase",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetAportacionClaseDto.class)
                            ,examples = @ExampleObject(
                            value = """
                                    [
                                      {
                                        "fecha": "2022-12-19",
                                        "aportaciones": {
                                          "Legumbres": 63.8,
                                          "Verduras": 41.2,
                                          "Frutos secos": 17.8
                                        }
                                      },
                                      {
                                        "fecha": "2022-12-12",
                                        "aportaciones": {
                                          "Legumbres": 28.8,
                                          "Verduras": 13.2,
                                          "Frutos secos": 19.3
                                        }
                                      }
                                    ]
                                    """
                    ))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha podido obtener la clase",
                    content = @Content),
    })
    @GetMapping("/clase/{id}")
    public ResponseEntity<List<GetAportacionClaseDto>> findAportacionesDeUnaClase(@Parameter(description = "Id de la clase") @PathVariable Long id) {

        if (!claseService.existById(id) || aportacionService.findAll().isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(aportacionDtoConverter.findAportacionesClase(claseService.findById(id).get()));
    }


    @Operation(summary = "Crea una aportacion")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = PostDetalleAportacionDto.class),
                examples = @ExampleObject(value = """
                        {
                          "claseId": 1,
                          "tipoAlimento": {
                            "3": 12.3,
                            "4": 4.8,
                            "5": 7.9
                          }
                        }
                        """)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado la aportación",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetNewAportacionDto.class)
                            , examples = @ExampleObject(
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
    public ResponseEntity<GetNewAportacionDto> create(@RequestBody PostDetalleAportacionDto dto) {
        Optional<Clase> clase = claseService.findById(dto.getClaseId());
        if (dto.getTipoAlimento() == null || dto.getClaseId() == null || clase.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        Aportacion nueva = aportacionService.createAportacion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(aportacionDtoConverter.newAportacionDto(nueva));
    }


    @Operation(summary = "Elimina una aportación a partir de un id dado",
            description = "Al borrar una aportación, se actualizan los KilosDisponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado correctamente la aportación",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAportacion(@PathVariable Long id) {
        Optional<Aportacion> aportacion = aportacionService.findById(id);
        if(aportacion.isPresent()){
            kilosDisponiblesService.removeKilosDisponiblesOfAnAportacion(aportacion.get());
            aportacionService.deleteById(id);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtiene todas las aportaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha obtenido el listado de aportaciones",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AportacionDtoN.class)
                            , examples = @ExampleObject(
                            value = """
                                    {//poner ejemplo
                                    }
                                    """
                    ))}),
            @ApiResponse(responseCode = "404",
                    description = "No se han podido obtener las aportaciones",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<List<AportacionDtoN>> getAll() {
        List<Aportacion> result = aportacionService.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result.stream().map(AportacionDtoN::of).collect(Collectors.toList()));
        }
    }//tb deberia darte el id y kilos de aportacion sumar para tener los kilos total de la aportacion

    @Operation(summary = "Edición de una aportación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado correctamente la aportación",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetNewAportacionDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 10,
                                                "clase": "2ª DAM",
                                                "fechaAportacion": "2022-12-19",
                                                "listadoDetalles": [
                                                    {
                                                        "numLinea": 1,
                                                        "alimento": "Garbanzos",
                                                        "kilos": 8.9
                                                    },
                                                    {
                                                        "numLinea": 2,
                                                        "alimento": "Dodotis",
                                                        "kilos": 7.0
                                                    },
                                                    {
                                                        "numLinea": 3,
                                                        "alimento": "Lentejas",
                                                        "kilos": 6.0
                                                    }
                                                ]
                                            }
                                            """
                            )})}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha indicado correctamente los datos para editar una aportación",
                    content = @Content)
    })
    @PutMapping("/{id}/linea/{num}/kg/{numKg}")
    public ResponseEntity<GetNewAportacionDto> edit(@Parameter(description = "Identificador de la aportación a editar")
                                                        @PathVariable Long id,
                                                    @Parameter(description = "Número de línea de la detalle de aportación")
                                                        @PathVariable Long num,
                                                    @Parameter(description = "Número de kilos que se van a editar")
                                                        @PathVariable Double numKg) {
        Optional<Aportacion> aportacion = aportacionService.findById(id);
        DetalleAportacion edit = aportacionService.findOneDetalleAportacion(id, num);

        if (aportacion.isEmpty() || edit == null || numKg == null || numKg < 0)
            return ResponseEntity.badRequest().build();

        edit.setCantidadKilos(numKg);
        kilosDisponiblesService.editOneKiloDisponible(edit);
        return ResponseEntity.ok(aportacionDtoConverter.aportacionToGetNewAportacionDto(edit.getAportacion()));
    }

    @Operation(summary = "Eliminar un detalle de aportacion de una aportación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha eliminado correctamente el detalle de aportación de la aportación pasada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetNewAportacionDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 10,
                                                "clase": "2ª DAM",
                                                "fechaAportacion": "2022-12-19",
                                                "listadoDetalles": [
                                                    {
                                                        "numLinea": 1,
                                                        "alimento": "Garbanzos",
                                                        "kilos": 8.9
                                                    },
                                                    {
                                                        "numLinea": 2,
                                                        "alimento": "Dodotis",
                                                        "kilos": 7.0
                                                    }
                                                ]
                                            }
                                            """
                            )})}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el detalle de aportación indicado",
                    content = @Content)
    })
    @DeleteMapping("/{id}/linea/{num}")
    public ResponseEntity<GetNewAportacionDto> deleteDetalleAportacion(@Parameter(description = "Identificador de la aportación a la que se le quiere eliminar un detalle de aportación")
                                                                           @PathVariable Long id,
                                                                       @Parameter(description = "Número de línea del detalle de aportación")
                                                                       @PathVariable Long num) {
        Optional<Aportacion> aportacion = aportacionService.findById(id);
        DetalleAportacion delete = aportacionService.findOneDetalleAportacion(id, num);

        if(aportacion.isEmpty() || delete == null)
            return ResponseEntity.notFound().build();

        kilosDisponiblesService.removeKilosDisponiblesOfADetalleAportacion(delete);
        aportacion.get().removeFromAportacion(delete);
        aportacionService.save(aportacion.get());
        return ResponseEntity.ok(aportacionDtoConverter.aportacionToGetNewAportacionDto(aportacion.get()));
    }

    //JERO
    @Operation(summary = "Este endpoint obtiene una aportacion por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado aportaciones",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetNewAportacionDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "id": 10,
                                                 "clase": "2ª DAM",
                                                 "fechaAportacion": "2022-10-24",
                                                 "listadoDetalles": [
                                                     {
                                                         "numLinea": 1,
                                                         "alimento": "Garbanzos",
                                                         "kilos": 15.0
                                                     },
                                                     {
                                                         "numLinea": 2,
                                                         "alimento": "Dodotis",
                                                         "kilos": 5.0
                                                     }
                                                 ]
                                             }
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado aportaciones",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetNewAportacionDto> getOneAportacion(@PathVariable Long id){
        Optional <Aportacion> result = aportacionService.findById(id);
        if (result.isPresent())
            return ResponseEntity.ok(
                    aportacionDtoConverter.newAportacionDto(result.get())
            );
        else
            return ResponseEntity.notFound().build();
    }

}
