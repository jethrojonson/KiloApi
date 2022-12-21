package com.salesianostriana.dam.kiloapi.dto.destinatario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@SuperBuilder
public class GetDestinatarioDetalleDto extends GetDestinatarioDto{
    private List<ListaDetallesCajaDto> cajas;
}
