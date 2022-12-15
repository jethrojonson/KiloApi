package com.salesianostriana.dam.kiloapi.controller;

import com.salesianostriana.dam.kiloapi.dto.tipoalimento.TipoAlimentoDto;
import com.salesianostriana.dam.kiloapi.model.TipoAlimento;
import com.salesianostriana.dam.kiloapi.service.TipoAlimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tipoAlimento")
@RequiredArgsConstructor
public class TipoAlimentoController {

    private final TipoAlimentoService tipoAlimentoService;

    /*
    @Operation(summary = "Obtiene un tipo de alimento específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado el tipo de alimento",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoAlimento.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha podido editar el tipo de alimento",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<TipoAlimentoDto> update(@RequestBody TipoAlimentoDto t, @PathVariable Long id){

        if(t.getNombre().isEmpty() || t.getNombre() == null )
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.of(
                tipoAlimentoService.findById(id)
                        .map(old -> {
                            old.setNombre(t.getNombre());
                            tipoAlimentoService.save(old);
                            return Optional.of(TipoAlimentoDto.builder().nombre(old.getNombre()).build());
                        })
                        .orElse(Optional.empty())
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoAlimento> getOne(@PathVariable Long id){

        if(!tipoAlimentoService.existById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.of(tipoAlimentoService.findById(id));
    }
    */
}
