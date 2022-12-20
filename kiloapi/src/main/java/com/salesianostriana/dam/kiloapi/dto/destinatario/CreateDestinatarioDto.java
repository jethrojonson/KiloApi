package com.salesianostriana.dam.kiloapi.dto.destinatario;

import com.salesianostriana.dam.kiloapi.model.Destinatario;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class CreateDestinatarioDto {

    private String nombre, direccion, personaContacto, telefono;

    public static Destinatario of(CreateDestinatarioDto createDestinatarioDto) {
        return Destinatario.builder()
                .nombre(createDestinatarioDto.getNombre())
                .direccion(createDestinatarioDto.getDireccion())
                .personaContacto(createDestinatarioDto.getPersonaContacto())
                .telefono(createDestinatarioDto.getTelefono())
                .build();
    }

}
