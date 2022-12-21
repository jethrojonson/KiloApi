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
        int contador = 0;
        double kilos = 0;
        int[] cajas = new int[d.getCajas().size()];
        for (Caja c : d.getCajas()) {
            cajas[contador] = c.getNumCaja();
            kilos += c.getKilosTotales();
            contador++;
        }
        return GetDestinatarioDTOM.builder()
                .id(d.getId())
                .nombre(d.getNombre())
                .personaContacto(d.getPersonaContacto())
                .telefono(d.getTelefono())
                .cajasNum(cajas)
                .direccion(d.getDireccion())
                .kgTotales(kilos)
                .build();
    }
}
