package com.salesianostriana.dam.kiloapi.dto.destinatario;

import com.salesianostriana.dam.kiloapi.model.Destinatario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@SuperBuilder
public class GetDestinatarioDto {

    private Long id;
    private String nombre, direccion, personaContacto, telefono;

    public static GetDestinatarioDto of(Destinatario destinatario) {
        return GetDestinatarioDto.builder()
                .id(destinatario.getId())
                .nombre(destinatario.getNombre())
                .direccion(destinatario.getDireccion())
                .personaContacto(destinatario.getPersonaContacto())
                .telefono(destinatario.getTelefono())
                .build();
    }

}
