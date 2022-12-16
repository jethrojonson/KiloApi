package com.salesianostriana.dam.kiloapi.controller;

import com.salesianostriana.dam.kiloapi.dto.aportacion.GetAportacionDto;
import com.salesianostriana.dam.kiloapi.dto.aportacion.AportacionDtoConverter;
import com.salesianostriana.dam.kiloapi.dto.aportacion.GetNewAportacionDto;
import com.salesianostriana.dam.kiloapi.dto.detalleaportacion.DetalleDtoConverter;
import com.salesianostriana.dam.kiloapi.dto.detalleaportacion.PostDetalleAportacionDto;
import com.salesianostriana.dam.kiloapi.model.TipoAlimento;
import com.salesianostriana.dam.kiloapi.service.AportacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aportacion")
@RequiredArgsConstructor
public class AportacionController {

    private final AportacionService aportacionService;
    private final AportacionDtoConverter aportacionDtoConverter;
    private final DetalleDtoConverter detalleDtoConverter;

    @GetMapping("/{id}")
    public ResponseEntity<GetAportacionDto> findOne(@PathVariable Long id){

        if(!aportacionService.existById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(aportacionDtoConverter.getAportacionDto(aportacionService.findById(id).get()));
    }


    @Operation(summary = "Crea una aportacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha obtenido el tipo de alimento",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetNewAportacionDto.class)
                            ,examples = @ExampleObject(
                            value = """
                                    {
                                        "id": 10,
                                        "fechaAportacion": "2022-12-16",
                                        "listadoDetalles": [
                                            {
                                                "numLinea": 1,
                                                "nombreAlimento": "Garbanzos",
                                                "cantidadAlimento": 4.5
                                            },
                                            {
                                                "numLinea": 2,
                                                "nombreAlimento": "Dodotis",
                                                "cantidadAlimento": 6.7
                                            }
                                        ]
                                    }
                                    """
                    ))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha podido obtener el tipo de alimento",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<GetNewAportacionDto> create(@RequestBody PostDetalleAportacionDto dto){
        if(dto.getTipoAlimento() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        detalleDtoConverter.getPostDtoToCreateDetalle(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(aportacionDtoConverter.newAportacionDto(dto));
    }

}
