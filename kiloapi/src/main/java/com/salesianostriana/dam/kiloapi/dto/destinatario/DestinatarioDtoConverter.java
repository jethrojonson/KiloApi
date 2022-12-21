package com.salesianostriana.dam.kiloapi.dto.destinatario;

import com.salesianostriana.dam.kiloapi.model.Destinatario;
import com.salesianostriana.dam.kiloapi.service.DestinatarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DestinatarioDtoConverter {

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

    public GetDestinatarioDto destinatarioToGetDestinatarioDto(Destinatario destinatario) {
        return GetDestinatarioDto.builder()
                .id(destinatario.getId())
                .nombre(destinatario.getNombre())
                .direccion(destinatario.getDireccion())
                .personaContacto(destinatario.getPersonaContacto())
                .telefono(destinatario.getTelefono())
                .build();
    }
}
