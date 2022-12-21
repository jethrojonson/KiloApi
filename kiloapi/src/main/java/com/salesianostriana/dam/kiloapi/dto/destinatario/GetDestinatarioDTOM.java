package com.salesianostriana.dam.kiloapi.dto.destinatario;

import com.salesianostriana.dam.kiloapi.model.Caja;
import com.salesianostriana.dam.kiloapi.model.Destinatario;
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

    public static GetDestinatarioDTOM of(Destinatario d) {
        double kgTotal = 0;

        for(Caja c : d.getCajas()){
            kgTotal += c.getKilosTotales();
        }

        return GetDestinatarioDTOM.builder()
                .id(d.getId())
                .nombre(d.getNombre())
                .direccion(d.getDireccion())
                .personaContacto(d.getPersonaContacto())
                .telefono(d.getTelefono())
                .kgTotales(kgTotal)
                .build();
    }
}
