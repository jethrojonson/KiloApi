package com.salesianostriana.dam.kiloapi.controller;

import com.salesianostriana.dam.kiloapi.dto.aportacion.*;
import com.salesianostriana.dam.kiloapi.dto.aportacion.PostDetalleAportacionDto;
import com.salesianostriana.dam.kiloapi.model.Aportacion;
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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/aportacion")
@RequiredArgsConstructor
@Tag(name = "Aportaciones", description = "Controller de aportaciones")
public class AportacionController {

    private final AportacionService aportacionService;
    private final ClaseService claseService;
    private final AportacionDtoConverter aportacionDtoConverter;


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
    public ResponseEntity<List<GetAportacionClaseDto>> findAportacionesDeUnaClase(@Parameter(description = "Id de la clase") @PathVariable Long id){

        if(!claseService.existById(id) || aportacionService.findAll().isEmpty())
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
        if(dto.getTipoAlimento() == null || dto.getClaseId() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        Aportacion nueva = aportacionService.createAportacion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(aportacionDtoConverter.newAportacionDto(nueva));
    }



    @Operation(summary = "Elimina una aportación a partir de un id dado",
            description = "Al borrar una aportación, se borran sus DetalleAportación asociados y se actualizan los KilosDisponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado correctamente la aportación",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAportacion(@PathVariable Long id) {
        if(aportacionService.findById(id).isPresent())
            claseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtiene todas las aportaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha obtenido el listado de aportaciones",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AportacionDtoN.class)
                            ,examples = @ExampleObject(
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
    public ResponseEntity<List<AportacionDtoN>> getAll(){
        List<Aportacion> result = aportacionService.findAll();
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(result.stream().map(AportacionDtoN::of).collect(Collectors.toList()));
        }
    }


}
