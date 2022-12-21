package com.salesianostriana.dam.kiloapi.controller;

import com.salesianostriana.dam.kiloapi.dto.tipoalimento.TipoAlimentoDto;
import com.salesianostriana.dam.kiloapi.dto.tipoalimento.TipoAlimentoDtoBasicN;
import com.salesianostriana.dam.kiloapi.dto.tipoalimento.TipoAlimentoDtoConverterN;
import com.salesianostriana.dam.kiloapi.model.TipoAlimento;
import com.salesianostriana.dam.kiloapi.service.KilosDisponiblesService;
import com.salesianostriana.dam.kiloapi.service.TipoAlimentoService;
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
import java.util.Optional;

@RestController
@RequestMapping("/tipoAlimento")
@RequiredArgsConstructor
@Tag(name = "Tipo de Alimento", description = "Controller de tipo de alimento")
public class TipoAlimentoController {

    private final TipoAlimentoService tipoAlimentoService;
    private final TipoAlimentoDtoConverterN tipoAlimentoDtoConverter;
    private final KilosDisponiblesService kilosDisponiblesService;


    @Operation(summary = "Edita un tipo de alimento específico")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TipoAlimentoDto.class),
                    examples = @ExampleObject(
                            value = """
                                    {
                                        "nombre": "Pasta"
                                    }
                            """)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado el tipo de alimento",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoAlimento.class)
                            , examples = @ExampleObject(
                            value = """
                                    {
                                        "id": 1,
                                        "nombre": "Pasta"
                                    }
                                    """
                    ))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha podido editar el tipo de alimento",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<TipoAlimentoDtoBasicN> update(@RequestBody TipoAlimentoDto t, @Parameter(description = "Id del tipo de alimento") @PathVariable Long id) {

        if (t.getNombre() == null || t.getNombre().isEmpty() || !tipoAlimentoService.existById(id))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.of(
                tipoAlimentoService.findById(id)
                        .map(old -> {
                            old.setNombre(t.getNombre());
                            tipoAlimentoService.save(old);
                            return Optional.of(tipoAlimentoDtoConverter.tipoAlimentoToTipoAlimentoDtoBasicN(tipoAlimentoService.findById(id).get()));
                        })
                        .orElse(Optional.empty())
        );

    }

    @Operation(summary = "Obtiene un tipo de alimento específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha obtenido el tipo de alimento",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoAlimento.class)
                            , examples = @ExampleObject(
                            value = """
                                    {
                                        "id": 1,
                                        "nombre": "Legumbres"
                                    }
                                    """
                    ))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha podido obtener el tipo de alimento",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<TipoAlimentoDtoBasicN> getOne(@Parameter(description = "Id del tipo de alimento") @PathVariable Long id) {

        if (!tipoAlimentoService.existById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(tipoAlimentoDtoConverter.tipoAlimentoToTipoAlimentoDtoBasicN(tipoAlimentoService.findById(id).get()));
    }

    @Operation(summary = "Obtiene todos los tipos de alimentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han obtenido todos los tipos de alimento",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoAlimentoDtoBasicN.class)
                            , examples = @ExampleObject(
                            value = """
                                    [
                                        {
                                            "id": 1,
                                            "nombre": "Congelados"
                                        },
                                        {
                                            "id": 2,
                                            "nombre": "Enlatados"
                                        },
                                        {
                                            "id": 3,
                                            "nombre": "Bebidas"
                                        }
                                    ]
                                    """
                    ))}),
            @ApiResponse(responseCode = "404",
                    description = "No se han podido obtener la lista de los tipos de alimento",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<List<TipoAlimentoDtoBasicN>> getAll() {

        List<TipoAlimento> tiposAlimentos = tipoAlimentoService.findAll();
        if (!tiposAlimentos.isEmpty()) {
            return ResponseEntity.ok(
                    tipoAlimentoService.findAll()
                            .stream()
                            .map(t -> tipoAlimentoDtoConverter.tipoAlimentoToTipoAlimentoDtoBasicN(t))
                            .toList()
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @Operation(summary = "Crea un tipo de alimento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado un tipo de alimento y devuelve su id y su nombre",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoAlimentoDtoBasicN.class)
                            , examples = @ExampleObject(
                            value = """
                                    {
                                        "nombre": "Lácteos"
                                    }
                                    """
                    ))}),
            @ApiResponse(responseCode = "400",
                    description = "Los datos introducidos son erróneos",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<TipoAlimentoDtoBasicN> crearTipoAlimento(@RequestBody TipoAlimentoDto nuevo) {
        if (nuevo.getNombre() != null){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(tipoAlimentoService.createTipoAlimento(nuevo));
        }


        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Elimina un tipo de alimento a partir de un id dado",
            description = "Al borrar  (poner política")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado correctamente el tipo de alimento",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTipoAlimento(@PathVariable Long id) {
        if(tipoAlimentoService.findById(id).isPresent() && tipoAlimentoService.comprobarBorradoTipoAlimento(id))
            kilosDisponiblesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}


