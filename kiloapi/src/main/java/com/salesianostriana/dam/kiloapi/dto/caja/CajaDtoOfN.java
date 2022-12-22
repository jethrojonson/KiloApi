package com.salesianostriana.dam.kiloapi.dto.caja;

import com.salesianostriana.dam.kiloapi.model.Caja;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CajaDtoOfN {

    private Long id;
    private String qr;
    private int numCaja;
    private double kilosTotales;
    private List<TieneDtoN> alimentos;
    private Long idDestinatario;
    private String nombreDestinatario;

   public static CajaDtoOfN of(Caja caja){
        return CajaDtoOfN.builder()
                .id(caja.getId())
                .qr(caja.getQr())
                .numCaja(caja.getNumCaja())
                .kilosTotales(caja.getKilosTotales())
                .alimentos(caja.getTieneList().stream().map(TieneDtoN::of).toList())
                .idDestinatario(caja.getDestinatario() != null ? caja.getDestinatario().getId() : null)
                .nombreDestinatario(caja.getDestinatario() != null ? caja.getDestinatario().getNombre() : null)
                .build();
    }
}
