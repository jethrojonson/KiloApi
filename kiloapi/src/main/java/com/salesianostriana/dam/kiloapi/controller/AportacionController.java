package com.salesianostriana.dam.kiloapi.controller;

import com.salesianostriana.dam.kiloapi.model.Aportacion;
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

    @GetMapping("/{id}")
    public ResponseEntity<Aportacion> findOne(@PathVariable Long id){
        if(!aportacionService.existById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.of(aportacionService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Aportacion> create(@RequestBody Aportacion a){
        if(a.getClase() == null || a.getDetalles() == null || a.getFecha() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(a);
    }

}
