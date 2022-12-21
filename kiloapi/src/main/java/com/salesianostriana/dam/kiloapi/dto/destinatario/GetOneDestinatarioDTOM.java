package com.salesianostriana.dam.kiloapi.dto.destinatario;

import com.salesianostriana.dam.kiloapi.model.Caja;
import com.salesianostriana.dam.kiloapi.model.Destinatario;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetOneDestinatarioDTOM {

    private Long id;
    private String nombre;
    private String direccion;
    private String personaContacto;
    private String telefono;
    private double kgTotales;
    private int cajasRecibidas;

    public static GetOneDestinatarioDTOM of(Destinatario d){
        double kgTotal = 0;

        for(Caja c : d.getCajas()){
            kgTotal += c.getKilosTotales();
        }

        return GetOneDestinatarioDTOM.builder()
                .id(d.getId())
                .nombre(d.getNombre())
                .direccion(d.getDireccion())
                .personaContacto(d.getPersonaContacto())
                .telefono(d.getTelefono())
                .kgTotales(kgTotal)
                .cajasRecibidas(d.getCajas().size())
                .build();
    }

}
