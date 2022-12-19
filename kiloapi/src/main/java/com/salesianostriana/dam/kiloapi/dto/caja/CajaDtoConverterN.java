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
                .qr(caja.getQr())
                .numCaja(caja.getNumCaja())
                .kilosTotales(caja.getKilosTotales())
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

        return CajaDtoPut.builder()
                .caja(caja)
                .listaAlimentos(auxList)
                .build();

    }
}


