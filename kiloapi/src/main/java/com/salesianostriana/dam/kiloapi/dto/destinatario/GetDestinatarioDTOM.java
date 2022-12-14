package com.salesianostriana.dam.kiloapi.dto.destinatario;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetDestinatarioDTOM {

    private Long id;
    private String nombre;
    private String direccion;
    private String personaContacto;
    private String telefono;
    private double kgTotales;

    private int [] cajasNum;
}
