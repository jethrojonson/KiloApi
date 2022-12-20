package com.salesianostriana.dam.kiloapi.controller;

import com.salesianostriana.dam.kiloapi.dto.aportacion.GetAportacionClaseDto;
import com.salesianostriana.dam.kiloapi.model.Destinatario;
import com.salesianostriana.dam.kiloapi.service.DestinatarioService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/destinatario")
@RequiredArgsConstructor
@Tag(name = "Destinatatios", description = "Controller de destinatarios")
public class DestinatarioController {

    private final DestinatarioService destinatarioService;


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

}
