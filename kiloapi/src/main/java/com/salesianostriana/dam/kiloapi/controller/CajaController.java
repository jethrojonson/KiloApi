package com.salesianostriana.dam.kiloapi.controller;

import com.salesianostriana.dam.kiloapi.dto.caja.CajaDtoBasicN;
import com.salesianostriana.dam.kiloapi.dto.caja.CajaDtoConverterN;
import com.salesianostriana.dam.kiloapi.dto.caja.CajaDtoN;
import com.salesianostriana.dam.kiloapi.dto.caja.CajaDtoOfN;
import com.salesianostriana.dam.kiloapi.dto.clase.GetOneClaseDtoJ;
import com.salesianostriana.dam.kiloapi.model.Caja;
import com.salesianostriana.dam.kiloapi.model.Clase;
import com.salesianostriana.dam.kiloapi.service.CajaService;
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

@RestController
@RequestMapping("/caja")
@RequiredArgsConstructor
@Tag(name = "Cajas", description = "Controller de cajas")
public class CajaController {

    private final CajaService cajaService;
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
//los kilos totales asegurar que sean dinámicos con las cajas, por ahora se quedan en 23 porque está instanciado así en el main







}
