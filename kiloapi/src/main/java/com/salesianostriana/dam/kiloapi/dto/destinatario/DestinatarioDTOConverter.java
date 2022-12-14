package com.salesianostriana.dam.kiloapi.dto.destinatario;

import com.salesianostriana.dam.kiloapi.model.Caja;
import com.salesianostriana.dam.kiloapi.model.Destinatario;
import com.salesianostriana.dam.kiloapi.service.DestinatarioService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DestinatarioDTOConverter {

    private final DestinatarioService destinatarioService;

    public GetDestinatarioDetalleDto destinatarioToGetDestinatarioDetalleDto(Destinatario destinatario) {
        return GetDestinatarioDetalleDto.builder()
                .id(destinatario.getId())
                .nombre(destinatario.getNombre())
                .direccion(destinatario.getDireccion())
                .personaContacto(destinatario.getPersonaContacto())
                .telefono(destinatario.getTelefono())
                .cajas(destinatarioService.getListaDetallesCajaDto(destinatario))
                .build();
    }

    public GetDestinatarioDTOM destinatarioToGetDestinatarioDTOM(Destinatario d) {
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

    public GetOneDestinatarioDTOM destinatarioToGetOneDestinatarioDTOM(Destinatario d) {
        double kilos = 0;
        for(Caja c : d.getCajas()){
            kilos += c.getKilosTotales();
        }
        return GetOneDestinatarioDTOM.builder()
                .id(d.getId())
                .nombre(d.getNombre())
                .personaContacto(d.getPersonaContacto())
                .telefono(d.getTelefono())
                .direccion(d.getDireccion())
                .kgTotales(kilos)
                .cajasRecibidas(d.getCajas().size())
                .build();
    }
}
