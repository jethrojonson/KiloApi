package com.salesianostriana.dam.kiloapi.controller;

import com.salesianostriana.dam.kiloapi.dto.clase.ClaseDTOM;
import com.salesianostriana.dam.kiloapi.dto.clase.ClaseDtoConverterJ;
import com.salesianostriana.dam.kiloapi.dto.clase.GetOneClaseDtoJ;
import com.salesianostriana.dam.kiloapi.dto.clase.NewClaseDTOM;
import com.salesianostriana.dam.kiloapi.model.Clase;
import com.salesianostriana.dam.kiloapi.service.ClaseService;
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


@RestController
@RequestMapping("/clase")
@RequiredArgsConstructor
@Tag(name = "Clases", description = "Controller de las clases")
public class ClaseController {

    private final ClaseService claseService;
    private final ClaseDtoConverterJ claseDtoConverter;

    @Operation(summary = "Elimina una clase por su identificador",
            description = "Al borrar una clase, no se eliminan sus aportaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado correctamente la clase indicada",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClase(@PathVariable Long id) {
        if (claseService.findById(id).isPresent())
            claseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtiene una clase por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado con éxito la clase indicada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetOneClaseDtoJ.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "2º DAM",
                                                "tutor": "Luismi",
                                                "numAportaciones": 13,
                                                "numTotalKilos": 24
                                            }
                                            """
                            )})}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la clase indicada",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetOneClaseDtoJ> getOneClass(@Parameter(description = "Identificador de la clase a buscar")
                                                           @PathVariable Long id) {
        Optional<Clase> clase = claseService.findById(id);
        if (clase.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(claseDtoConverter.claseToGetOneClaseDto(clase.get()));
    }

    //JERO PART

    @Operation(summary = "Este endpoint obtiene la lista de clases simplificadas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado clases",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ClaseDTOM.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "id" : 1,
                                                    "nombre" : "1º DAM",
                                                    "tutor" : "Miguel Campos"
                                                },
                                                {
                                                    "id" : 2,
                                                    "nombre" : "2º DAM",
                                                    "tutor" : "Luis Miguel"
                                                },
                                                {
                                                    "id" : 1,
                                                    "nombre" : "1º AyF",
                                                    "tutor" : "Luis"
                                                }
                                            ]
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado clases",
                    content = @Content
            )
    })
    @GetMapping("/")
    public ResponseEntity<List<ClaseDTOM>> getAllClases() {
        List<Clase> result = claseService.getAll();
        if (result.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(
                    result.stream()
                            .map(c -> claseDtoConverter.claseToClaseDTOM(c))
                            .toList()
            );
    }

    @Operation(summary = "Este endpoint añade una nueva clase")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha añadido la clase correctamente",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ClaseDTOM.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id" : 1,
                                                "nombre" : "1º DAM",
                                                "tutor" : "Miguel Campos"
                                            }
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "400",
                    description = "Cuerpo de la petición incorrecto",
                    content = @Content
            )
    })
    @PostMapping("/")
    public ResponseEntity<ClaseDTOM> addNewClase(@RequestBody NewClaseDTOM newClase) {
        Clase toAdd = claseDtoConverter.newClaseToClase(newClase);
        if (toAdd.getTutor() != null & toAdd.getNombre() != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    claseDtoConverter.claseToClaseDTOM(
                            claseService.save(toAdd)
                    )
            );
        else
            return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Este endpoint actualiza una clase existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha actualizado la información de la clase",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ClaseDTOM.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id" : 1,
                                                "nombre" : "nuevo nombre",
                                                "tutor" : "nuevo tutor"
                                            }
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "400",
                    description = "Cuerpo de la petición incorrecto",
                    content = @Content
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClaseDTOM> updateClase(@PathVariable Long id, @RequestBody NewClaseDTOM newClase) {
        Clase toUpdate;
        if(claseService.findById(id).isPresent())
            toUpdate = claseService.findById(id).get();
        else
            return ResponseEntity.badRequest().build();
        if(newClase.getNombre()!=null&&newClase.getTutor()!=null) {
            toUpdate.setNombre(newClase.getNombre());
            toUpdate.setTutor(newClase.getTutor());
            return ResponseEntity.ok(
                    claseDtoConverter.claseToClaseDTOM(
                            claseService.save(toUpdate)
                    )
            );
        }
        else
            return ResponseEntity.badRequest().build();
    }


}