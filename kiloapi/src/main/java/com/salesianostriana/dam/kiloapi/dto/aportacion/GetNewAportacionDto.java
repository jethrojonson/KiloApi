package com.salesianostriana.dam.kiloapi.dto.aportacion;

import com.salesianostriana.dam.kiloapi.dto.detalleaportacion.GetDetallesDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetNewAportacionDto {

    private Long id;
    private LocalDate fechaAportacion;
    private List<GetDetallesDto> listadoDetalles;

}
