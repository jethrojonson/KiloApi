package com.salesianostriana.dam.kiloapi.controller;

import com.salesianostriana.dam.kiloapi.dto.caja.*;
import com.salesianostriana.dam.kiloapi.model.*;
import com.salesianostriana.dam.kiloapi.service.CajaService;
import com.salesianostriana.dam.kiloapi.service.DestinatarioService;
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
@RequestMapping("/caja")
@RequiredArgsConstructor
@Tag(name = "Cajas", description = "Controller de cajas")
public class CajaController {

    private final CajaService cajaService;
    private final KilosDisponiblesService kilosDisponiblesService;
    private final DestinatarioService destinatarioService;
    private final CajaDtoConverterN cajaDtoConverter;

    private final TipoAlimentoService tipoAlimentoService;


    @Operation(summary = "Obtiene todas las cajas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha obtenido el listado de cajas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CajaDtoN.class)
                            , examples = @ExampleObject(
                            value = """
                                    {//poner ejemplo
                                    }
                                    """
                    ))}),
            @ApiResponse(responseCode = "404",
                    description = "No se han podido obtener las cajas",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<List<CajaDtoN>> getAllCajas() {
        if (!cajaService.findAll().isEmpty()) {
            return ResponseEntity.ok(
                    cajaService.findAll()
                            .stream()
                            .map(c -> cajaDtoConverter.CajaToCajaDto(c))
                            .toList()
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }//probar a ponerlo con el método of y el collectors en vez de dos converter


    @Operation(summary = "Crea una caja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado una caja correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Caja.class)
                            , examples = @ExampleObject(
                            value = """
                                    {
                                        "numCaja" : 3,
                                        "qr":"puntitos bonitos"
                                    }
                                    """
                    ))}),
            @ApiResponse(responseCode = "400",
                    description = "Los datos introducidos son erróneos",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<CajaDtoN> nuevaCaja(@RequestBody CajaDtoBasicN nuevo) {
        if (nuevo.getQr() == "") {
            return ResponseEntity.badRequest().build();
        }
        CajaDtoN saved = cajaDtoConverter.CajaDtoBasicNtoCajaDtoN(nuevo);//doble converter??
        Caja cajita = cajaDtoConverter.CajaDtoToCaja(saved);
        cajaService.save(cajita);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    @Operation(summary = "Obtiene una caja a partir de un id dado, incluye una lista con los alimentos que contiene: id tipo alimento, nombre tipo, cantidad kilos; y los datos del destinatario: id, nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado con éxito la clase indicada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Caja.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                               "numCaja" : 3,
                                               "qr":"puntitos bonitos"
                                            }
                                            """
                            )})}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la clase indicada",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CajaDtoOfN> getOneCaja(@PathVariable Long id) {
        return ResponseEntity.of(cajaService.findById(id).map(CajaDtoOfN::of));
    }

    @Operation(summary = "Edita un tipo de alimento específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado el alimento de la caja",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CajaDtoPut.class)
                            , examples = @ExampleObject(
                            value = """
                                    {
                                      "id": 0,
                                      "qr": "string",
                                      "numCaja": 0,
                                      "kilosTotales": 0,
                                      "listaAlimentos": [
                                        {
                                          "id": 0,
                                          "nombre": "string",
                                          "cantidadKgs": 0
                                        }
                                      ]
                                    }
                                    """
                    ))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha podido editar los kilos de la caja",
                    content = @Content),
    })
    @PutMapping("/{idC}/tipo/{idA}/kg/{kgs}")
    public ResponseEntity<CajaDtoPut> editarKilosCaja (@PathVariable Long idC, @PathVariable Long idA, @PathVariable Double kgs){

        double cantDisp = kilosDisponiblesService.findById(idA).get().getCantidadDisponible();

        if(idC == null || idA == null || kgs == null || cantDisp < kgs)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        else if (kgs == 0)
            return ResponseEntity.ok(cajaDtoConverter.createDtoPut(cajaService.findById(idC).get()));
        else
            return ResponseEntity.ok(cajaDtoConverter.createDtoPut(cajaService.changeTipoAlimentoAmount(idC, idA, kgs)));
    }

    @Operation(summary = "Elimina una caja específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado la caja",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Caja> delete (@PathVariable Long id){

        if(cajaService.existById(id))
            cajaService.deleteById(cajaService.preRemoveAlimentos(id));

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @Operation(summary = "Edita un tipo de alimento específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha asignado la caja al destinatario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CajaDtoPut.class)
                            , examples = @ExampleObject(
                            value = """
                                    {
                                      "id": 1,
                                      "qr": "https://example.com",
                                      "numCaja": 2,
                                      "kilosTotales": 23.4,
                                      "listaAlimentos": [
                                        {
                                          "id": 3,
                                          "nombre": "Pasta",
                                          "cantidadKgs": 12.3
                                        }
                                      ]
                                    }
                                    """
                    ))}),
            @ApiResponse(responseCode = "400",
                    description = "No se la podido asignar una caja al destinatario",
                    content = @Content),
    })
    @PostMapping("/{idC}/destinatario/{idD}")
    public ResponseEntity<CajaDtoPut> asignarDestinatario(@PathVariable Long idC, @PathVariable Long idD){
        if(idC == null || idD == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        Optional<Caja> caja = cajaService.findById(idC);
        caja.get().setDestinatario(destinatarioService.findById(idD).get());

        return ResponseEntity.ok(cajaDtoConverter.createDtoPut(caja.get()));
    }

    @Operation(summary = "Añadir la cantidad de kilos de un tipo de alimento a una caja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha añadido correctamente los kilos indicados del tipo alimento a la caja correspondiente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CajaDtoPut.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 9,
                                                "qr": "No existe",
                                                "numCaja": 1,
                                                "kilosTotales": 11.3,
                                                "alimentos": [
                                                    {
                                                        "id": 4,
                                                        "nombre": "Garbanzos",
                                                        "kgs": 6.9
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
                    description = "No se han añadido los kgs del tipo de alimento a la caja ya que los datos son erróneos",
                    content = {@Content})
    })
    @PostMapping("/{id}/tipo/{idTipoAlimento}/kg/{cantidad}")
    public ResponseEntity<CajaDtoPut> addTipoAlimentoToCaja(@Parameter(description = "Identificador de la caja")
                                                            @PathVariable Long id,
                                                            @Parameter(description = "Identificador del tipo alimento que se quiere añadir")
                                                            @PathVariable Long idTipoAlimento,
                                                            @Parameter(description = "Cantidad de kilos que se quieren aññadir del tipo de alimento")
                                                            @PathVariable Double cantidad) {
        Optional<Caja> caja = cajaService.findById(id);
        Optional<TipoAlimento> tipoAlimento = tipoAlimentoService.findById(idTipoAlimento);

        if(caja.isEmpty() || tipoAlimento.isEmpty() || cantidad == null ||
                tipoAlimento.get().getKilosDisponibles().getCantidadDisponible() < cantidad) {
            return ResponseEntity.badRequest().build();
        } else {
            cajaService.addTipoAlimentoToCaja(caja.get(), tipoAlimento.get(), cantidad);
            return ResponseEntity.ok(cajaDtoConverter.cajaToGetCajaDtoPut(caja.get()));
        }
    }

    @Operation(summary = "Edición de una caja")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CajaDtoBasicN.class),
                    examples = @ExampleObject(value = """
                            {
                                "qr": "No existe",
                                "numCaja": 1
                            }
                            """)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado correctamente la caja",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CajaDtoPut.class),
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
    public ResponseEntity<CajaDtoPut> edit(@RequestBody CajaDtoBasicN createCajaDto,
                                           @Parameter(description = "Identificador de la clase a editar")
                                           @PathVariable Long id) {
        Optional<Caja> caja = cajaService.findById(id);
        if(caja.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else if(createCajaDto.getQr() == null || createCajaDto.getNumCaja() == null || createCajaDto.getNumCaja() < 0) {
            return ResponseEntity.badRequest().build();
        } else {
            Caja edit = createCajaDto.of(createCajaDto);
            return ResponseEntity.of(
                    caja.map(old -> {
                        old.setQr(edit.getQr());
                        old.setNumCaja(edit.getNumCaja());
                        return Optional.of(cajaDtoConverter.cajaToGetCajaDtoPut(old));
                    }).orElse(Optional.empty())
            );
        }
    }

}
