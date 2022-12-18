package com.salesianostriana.dam.kiloapi.controller;

import com.salesianostriana.dam.kiloapi.dto.ranking.GetRankingQueryDto;
import com.salesianostriana.dam.kiloapi.dto.ranking.RankingDtoConverter;
import com.salesianostriana.dam.kiloapi.dto.ranking.ReturnRankingDto;
import com.salesianostriana.dam.kiloapi.service.ClaseService;
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
public class RankingController {

    private final ClaseService claseService;
    private final RankingDtoConverter dtoConverter;

    @GetMapping("/")
    public ResponseEntity<List<ReturnRankingDto>> ranking(){
        if(claseService.ranking().isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        List<GetRankingQueryDto> aux = claseService.ranking();

        return ResponseEntity.ok(dtoConverter.returnRanking(aux));
    }

}
