package com.salesianostriana.dam.kiloapi.dto.caja;

import com.salesianostriana.dam.kiloapi.model.Caja;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CajaDtoBasicN {

    private String qr;
    private Integer numCaja;

    public static Caja of(CajaDtoBasicN createCajaDto) {
        return Caja.builder()
                .qr(createCajaDto.getQr())
                .numCaja(createCajaDto.getNumCaja())
                .build();
    }

}
