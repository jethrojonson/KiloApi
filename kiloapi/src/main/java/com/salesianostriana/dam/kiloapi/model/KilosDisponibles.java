package com.salesianostriana.dam.kiloapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo_alimento_id", foreignKey = @ForeignKey(name = "FK_KILOSDISP_TIPOALIMENTO"))
    @JsonIgnore
    @MapsId
    private TipoAlimento tipoAlimento;

    private double cantidadDisponible;

}
