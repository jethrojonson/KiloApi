package com.salesianostriana.dam.kiloapi.dto.destinatario;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class ListaTipoAlimentoDto {

    private String tipoAlimento;
    private Double cantidadKgs;

}
