package com.salesianostriana.dam.kiloapi.controller;

import com.salesianostriana.dam.kiloapi.dto.destinatario.DestinatarioDTOConverter;
import com.salesianostriana.dam.kiloapi.dto.destinatario.GetDestinatarioDTOM;
import com.salesianostriana.dam.kiloapi.dto.destinatario.GetOneDestinatarioDTOM;
import com.salesianostriana.dam.kiloapi.model.Destinatario;
import com.salesianostriana.dam.kiloapi.service.DestinatarioService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/destinatario")
@RequiredArgsConstructor
@Tag(name = "Destinatatios", description = "Controller de destinatarios")
public class DestinatarioController {

    private final DestinatarioService destinatarioService;
    private final DestinatarioDTOConverter destinatarioDTOConverter;


    @Operation(summary = "Elimina un destinatario específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado el destinatario",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Destinatario> deleteDestinatario(@PathVariable Long id) {

        Optional<Destinatario> destinatario = destinatarioService.findById(id);

        if (destinatarioService.existById(id)) {
            destinatarioService.delete(destinatario.get());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado destinatarios",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetDestinatarioDTOM.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "id": 6,
                                                    "nombre": "Asociación Don Bosco",
                                                    "direccion": "Calle Tu Casa",
                                                    "personaContacto": "Bosco",
                                                    "telefono": "678543234",
                                                    "kgTotales": 123.2,
                                                    "cajasNum": [
                                                        1,
                                                        3
                                                    ]
                                                },
                                                {
                                                    "id": 7,
                                                    "nombre": "Asociación MAría Auxiliadora",
                                                    "direccion": "Calle Pepito",
                                                    "personaContacto": "María",
                                                    "telefono": "675221094",
                                                    "kgTotales": 15.6,
                                                    "cajasNum": [
                                                        2
                                                    ]
                                                }
                                            ]
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "No se han econtrado destinatarios",
                    content = @Content
            )
    })
    @GetMapping("/")
    public ResponseEntity<List<GetDestinatarioDTOM>> getAllDestinatarios() {
        List<Destinatario> result = destinatarioService.getAll();
        if (result.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(
                    result.stream()
                            .map(d -> destinatarioDTOConverter.destinatarioToGetDestinatarioDTOM(d))
                            .toList()
            );
    }

    @Operation(summary = "Este endpoint obtiene un destinatario en base a su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado al destinatario",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetOneDestinatarioDTOM.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 7,
                                                "nombre": "Asociación MAría Auxiliadora",
                                                "direccion": "Calle Pepito",
                                                "personaContacto": "María",
                                                "telefono": "675221094",
                                                "kgTotales": 15.6,
                                                "cajasRecibidas": 1
                                            }
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado al destinatario",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetOneDestinatarioDTOM> GetOneDestinatario(@PathVariable Long id) {
        Optional<Destinatario> result = destinatarioService.findById(id);
        if (result.isPresent())
            return ResponseEntity.ok(
                    destinatarioDTOConverter.destinatarioToGetOneDestinatarioDTOM(result.get())
            );
        else
            return ResponseEntity.notFound().build();
    }
}
