package com.salesianostriana.dam.kiloapi.model;

import com.salesianostriana.dam.kiloapi.repos.TipoAlimentoRepository;
import com.salesianostriana.dam.kiloapi.service.TipoAlimentoService;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class KilosDisponibles {

    @OneToOne
    @JoinColumn(name = "tipo_alimento", foreignKey = @ForeignKey(name = "FK_KILOSDISP_TIPOALIMENTO"))
    @MapsId("tipo_alimento_id")
    private TipoAlimento tipoAlimento;

    @Id
    private Long id;

    private double catidadDisponible;

}
