package com.salesianostriana.dam.kiloapi.dto.caja;

import com.salesianostriana.dam.kiloapi.model.Caja;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CajaDtoOfN {

    //datos que deben ir en la respuesta de pedir una caja
    private List<TieneDTO> alimentos
    private double cantidadKilos;
    private Long idDestinatario;
    private String nombreDestinatario;

   public static CajaDtoOfN of(Caja caja){
        return CajaDtoOfN.builder()
                .idTipoAlimento(caja.getTieneList().) //for each
               .nombreTipoAlimento(caja.)
                .idDestinatario(caja.getDestinatario().getId())
                .build();
    }
}
