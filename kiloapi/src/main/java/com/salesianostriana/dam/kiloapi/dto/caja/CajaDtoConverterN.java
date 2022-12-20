package com.salesianostriana.dam.kiloapi.dto.caja;

import com.salesianostriana.dam.kiloapi.model.Caja;
import com.salesianostriana.dam.kiloapi.service.CajaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CajaDtoConverterN {

    private final CajaService cajaService;

    public CajaDtoN CajaToCajaDto(Caja caja){
        return CajaDtoN.builder()
                .id(caja.getId())
                .qr(caja.getQr())
                .numCaja(caja.getNumCaja())
                .kilosTotales(caja.getKilosTotales())
                .build();
    }

    public CajaDtoN CajaDtoBasicNtoCajaDtoN (CajaDtoBasicN cajaDtoBasicN){
        return CajaDtoN.builder()
                .qr(cajaDtoBasicN.getQr())
                .numCaja(cajaDtoBasicN.getNumCaja())
                .build();
    }

    public Caja CajaDtoToCaja(CajaDtoN cajaDtoN){
        return Caja.builder()
                .id(cajaDtoN.getId())
                .qr(cajaDtoN.getQr())
                .numCaja(cajaDtoN.getNumCaja())
                .kilosTotales(cajaDtoN.getKilosTotales())
                .build();
    }
}


