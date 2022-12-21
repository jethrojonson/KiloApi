package com.salesianostriana.dam.kiloapi.controller;

import com.salesianostriana.dam.kiloapi.dto.aportacion.GetAportacionClaseDto;
import com.salesianostriana.dam.kiloapi.dto.caja.CajaDtoBasicN;
import com.salesianostriana.dam.kiloapi.dto.caja.CajaDtoPut;
import com.salesianostriana.dam.kiloapi.dto.destinatario.CreateDestinatarioDto;
import com.salesianostriana.dam.kiloapi.dto.destinatario.DestinatarioDtoConverter;
import com.salesianostriana.dam.kiloapi.dto.destinatario.GetDestinatarioDetalleDto;
import com.salesianostriana.dam.kiloapi.dto.destinatario.GetDestinatarioDto;
import com.salesianostriana.dam.kiloapi.model.Caja;
import com.salesianostriana.dam.kiloapi.model.Destinatario;
import com.salesianostriana.dam.kiloapi.service.DestinatarioService;
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

import java.util.Optional;

@RestController
@RequestMapping("/destinatario")
@RequiredArgsConstructor
@Tag(name = "Destinatatios", description = "Controller de destinatarios")
public class DestinatarioController {

    private final DestinatarioService destinatarioService;
    private final DestinatarioDtoConverter destinatarioDtoConverter;


    @Operation(summary = "Elimina un destinatario específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado el destinatario",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Destinatario> deleteDestinatario (@PathVariable Long id){

        Optional<Destinatario> destinatario = destinatarioService.findById(id);

        if (destinatarioService.existById(id)){
            destinatarioService.delete(destinatario.get());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Creación de un destinatario")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CreateDestinatarioDto.class),
                    examples = @ExampleObject(value = """
                            {
                                "nombre": "Asociación María Auxiliadora",
                                "direccion": "Calle María Auxiliadora Nº24",
                                "personaContacto": "Gonzalo",
                                "telefono": "654789453"
                            }
                            """)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado un nuevo destinatario con éxito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetDestinatarioDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 7,
                                                "nombre": "Asociación María Auxiliadora",
                                                "direccion": "Calle María Auxiliadora Nº24",
                                                "personaContacto": "Gonzalo",
                                                "telefono": "654789453"
                                            }
                                            """
                            )})

                    }),
            @ApiResponse(responseCode = "400",
                    description = "No se ha creado el destinatario ya que se han introducido datos erróneos",
                    content = {@Content})
    })
    @PostMapping("/")
    public ResponseEntity<GetDestinatarioDto> create(@RequestBody CreateDestinatarioDto createDestinatarioDto) {
        if(createDestinatarioDto.getNombre() == null || createDestinatarioDto.getDireccion() == null
                || createDestinatarioDto.getPersonaContacto() == null || createDestinatarioDto.getTelefono() == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(destinatarioService.createDestinatario(createDestinatarioDto));
        }
    }

    @Operation(summary = "Obtención de los detalles de un destinatario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado con éxito los detalles del destinatario indicado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetDestinatarioDetalleDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 7,
                                                "nombre": "Asociación Don Bosco",
                                                "direccion": "Calle Juan Bosco",
                                                "personaContacto": "Bosco",
                                                "telefono": "678543234",
                                                "cajas": [
                                                    {
                                                        "numCaja": 1,
                                                        "kgsTotales": 7.4,
                                                        "alimentos": [
                                                            {
                                                                "tipoAlimento": "Garbanzos",
                                                                "cantidadKgs": 3.0
                                                            },
                                                            {
                                                                "tipoAlimento": "Dodotis",
                                                                "cantidadKgs": 3.1
                                                            },
                                                            {
                                                                "tipoAlimento": "Lentejas",
                                                                "cantidadKgs": 1.3
                                                            }
                                                        ]
                                                    }
                                                ]
                                            }
                                            """
                            )})
                    }),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado los detalles del destinatario que se ha pasado",
                    content = {@Content})
    })
    @GetMapping("/{id}/detalle")
    public ResponseEntity<?> getOneDestinatarioDetallado(@Parameter(description = "Identificador del destinatario que se quiere buscar") @PathVariable Long id) {
        Optional<Destinatario> destinatario = destinatarioService.findById(id);
        if(destinatario.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(destinatarioDtoConverter.destinatarioToGetDestinatarioDetalleDto(destinatario.get()));
    }




    @Operation(summary = "Edita a un destinatario")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = GetDestinatarioDto.class),
                    examples = @ExampleObject(value = """
                            {   
                                "id":4,
                                "nombre": "Miguel",
                                "dirección": "Calle sin nombre, número",
                                "personaContacto": "Ángel",
                                "teléfono": "954761259"
                            }
                            """)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado la aportación correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetDestinatarioDto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 9,
                                                "qr": "No existe",
                                                "numCaja": 1,
                                                "kilosTotales": 7.4,
                                                "alimentos": [
                                                    {
                                                        "id": 4,
                                                        "nombre": "Garbanzos",
                                                        "kgs": 3.0
                                                    },
                                                    {
                                                        "id": 5,
                                                        "nombre": "Dodotis",
                                                        "kgs": 3.1
                                                    },
                                                    {
                                                        "id": 6,
                                                        "nombre": "Lentejas",
                                                        "kgs": 1.3
                                                    }
                                                ]
                                            }
                                            """
                            )})
                    }),
            @ApiResponse(responseCode = "400",
                    description = "No se ha editado correctamente la caja",
                    content = {@Content}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la clase a editar",
                    content = {@Content})
    })

    @PutMapping("/{id}")
    public ResponseEntity<GetDestinatarioDto> update (@PathVariable Long id, @RequestBody CreateDestinatarioDto cambio) {
        Optional<Destinatario> destinatario = destinatarioService.findById(id);
        if (destinatario.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else if (cambio.getNombre() == null || cambio.getDireccion() == null || cambio.getPersonaContacto() == null || cambio.getTelefono() == null) {
            return ResponseEntity.badRequest().build();
        } else {
            Destinatario edit = CreateDestinatarioDto.of(cambio);
            return ResponseEntity.of(
                    destinatario.map(old -> {
                        old.setNombre(edit.getNombre());
                        old.setDireccion(edit.getDireccion());
                        old.setPersonaContacto((edit.getPersonaContacto()));
                        old.setTelefono((edit.getTelefono()));
                        return Optional.of(destinatarioDtoConverter.destinatarioToGetDestinatarioDto(old));
                    }).orElse(Optional.empty())
            );
        }
    }

}
