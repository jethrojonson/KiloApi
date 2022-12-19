package com.salesianostriana.dam.kiloapi.controller;

import com.salesianostriana.dam.kiloapi.dto.kilosdisponibles.GetKilosDisponiblesDto;
import com.salesianostriana.dam.kiloapi.dto.ranking.GetRankingQueryDto;
import com.salesianostriana.dam.kiloapi.dto.ranking.RankingDtoConverter;
import com.salesianostriana.dam.kiloapi.dto.ranking.ReturnRankingDto;
import com.salesianostriana.dam.kiloapi.service.ClaseService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
@RequiredArgsConstructor

@Tag(name = "Ranking", description = "Controller del ranking")
public class RankingController {

    private final ClaseService claseService;
    private final RankingDtoConverter dtoConverter;

    @Operation(summary = "Obtiene el ranking de las aportaciones de las clases")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha obtenido el ranking de las aportaciones de las clases",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetKilosDisponiblesDto.class)
                            , examples = @ExampleObject(
                            value = """
                                    [
                                      {
                                        "posicion": 1,
                                        "clase": "2ºDAM",
                                        "numeroDeAportaciones": 3,
                                        "mediaDeKgPorAportacion": 6.67,
                                        "kgTotalesAportados": 60
                                      },
                                      {
                                        "posicion": 2,
                                        "clase": "1ºDAM",
                                        "numeroDeAportaciones": 4,
                                        "mediaDeKgPorAportacion": 4.36,
                                        "kgTotalesAportados": 53.2
                                      }
                                    ]
                                    """
                    ))}),
            @ApiResponse(responseCode = "404",
                    description = "Ninguna clase ha hecho aportaciones",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<List<ReturnRankingDto>> ranking(){
        if(claseService.ranking().isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        List<GetRankingQueryDto> aux = claseService.ranking();

        return ResponseEntity.ok(dtoConverter.returnRanking(aux));
    }

}
