package com.salesianostriana.dam.kiloapi.controller;

import com.salesianostriana.dam.kiloapi.dto.aportacion.GetAportacionDto;
import com.salesianostriana.dam.kiloapi.dto.aportacion.converter.DtoConverter;
import com.salesianostriana.dam.kiloapi.dto.detalleaportacion.PostDetalleAportacionDto;
import com.salesianostriana.dam.kiloapi.model.DetalleAportacion;
import com.salesianostriana.dam.kiloapi.service.AportacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aportacion")
@RequiredArgsConstructor
public class AportacionController {

    private final AportacionService aportacionService;
    private final DtoConverter dtoConverter;

    @GetMapping("/{id}")
    public ResponseEntity<GetAportacionDto> findOne(@PathVariable Long id){

        if(!aportacionService.existById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(dtoConverter.getAportacionDto(aportacionService.findById(id).get()));
    }

    /*
    @PostMapping("/")
    public ResponseEntity<DetalleAportacion> create(@RequestBody PostDetalleAportacionDto a){
        if(a.getFecha() == null || a.getTipoAlimentoYPeso() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(a);
    }
     */

}
