package com.salesianostriana.dam.kiloapi.dto.caja;

import com.salesianostriana.dam.kiloapi.model.Caja;
import com.salesianostriana.dam.kiloapi.service.CajaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
                .tiene(caja.getTieneList())
                .build();
    }

    public Caja CajaDtoBasicNtoCaja (CajaDtoBasicN cajaDtoBasicN){
        return Caja.builder()
                .qr(cajaDtoBasicN.getQr())
                .numCaja(cajaDtoBasicN.getNumCaja())
                .build();
    }


    public CajaDtoPut createDtoPut(Caja caja){

        List<CajaTipoAlimentoDto> auxList = new ArrayList<>();

        caja.getTieneList().forEach(t -> {
            CajaTipoAlimentoDto dto = CajaTipoAlimentoDto.builder()
                    .id(t.getTipoAlimento().getId())
                    .nombre(t.getTipoAlimento().getNombre())
                    .cantidadKgs(t.getCantidadKgs())
                    .build();
            auxList.add(dto);
        });

        /*
        private Long id;
    private String qr;
    private int numCaja;
    private double kilosTotales;
         */

        return CajaDtoPut.builder()
                .id(caja.getId())
                .qr(caja.getQr())
                .numCaja(caja.getNumCaja())
                .kilosTotales(caja.getKilosTotales())
                .listaAlimentos(auxList)
                .build();

    }

    public CajaDtoPut cajaToGetCajaDtoPut(Caja caja) {
        return CajaDtoPut.builder()
                .id(caja.getId())
                .qr(caja.getQr())
                .numCaja(caja.getNumCaja())
                .kilosTotales(caja.getKilosTotales())
                .listaAlimentos(cajaService.findAlimentosOfACaja(caja.getId()))
                .build();
    }
}


