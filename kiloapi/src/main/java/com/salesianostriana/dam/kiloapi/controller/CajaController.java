package com.salesianostriana.dam.kiloapi.controller;

import com.salesianostriana.dam.kiloapi.dto.caja.*;
import com.salesianostriana.dam.kiloapi.model.*;
import com.salesianostriana.dam.kiloapi.service.CajaService;
import com.salesianostriana.dam.kiloapi.service.DestinatarioService;
import com.salesianostriana.dam.kiloapi.service.KilosDisponiblesService;
import com.salesianostriana.dam.kiloapi.service.TipoAlimentoService;
import io.swagger.v3.oas.annotations.Operation;
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
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/caja")
@RequiredArgsConstructor
@Tag(name = "Cajas", description = "Controller de cajas")
public class CajaController {

    private final CajaService cajaService;
    private final KilosDisponiblesService kilosDisponiblesService;
    private final DestinatarioService destinatarioService;
    private final CajaDtoConverterN cajaDtoConverter;


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
    public ResponseEntity<Caja> nuevaCaja(@RequestBody CajaDtoBasicN nuevo) {
        if (nuevo.getQr() == "") {
            return ResponseEntity.badRequest().build();
        }
        Caja saved = cajaDtoConverter.CajaDtoBasicNtoCaja(nuevo);
        cajaService.save(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    @Operation(summary = "Obtiene una caja a partir de un id dado")
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
                                                y mas
                                            }
                                            """
                            )})}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la clase indicada",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CajaDtoOfN> getOneCaja(@PathVariable Long id) {
        ResponseEntity<CajaDtoOfN> CajaDtoOfN = null;
        return CajaDtoOfN;
       // return ResponseEntity.of(cajaService.findById(id).map(CajaDtoOfN::of)); esto es lo que quiero que ponga lo otro es provisional hasta que funcione el OF
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

}
