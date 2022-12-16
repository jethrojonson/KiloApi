package com.salesianostriana.dam.kiloapi.controller;

import com.salesianostriana.dam.kiloapi.dto.clase.ClaseDtoConverterJ;
import com.salesianostriana.dam.kiloapi.dto.clase.GetOneClaseDtoJ;
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
        if(claseService.findById(id).isPresent())
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
    public ResponseEntity<GetOneClaseDtoJ> getOneClass(@PathVariable Long id) {
        Optional<Clase> clase = claseService.findById(id);
        if(clase.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(claseService.getCntKgs(id));
    }

}