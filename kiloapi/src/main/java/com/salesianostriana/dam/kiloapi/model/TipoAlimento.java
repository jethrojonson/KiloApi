package com.salesianostriana.dam.kiloapi.model;

import com.salesianostriana.dam.kiloapi.repos.TipoAlimentoRepository;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TipoAlimento {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "tipoAlimento", fetch = FetchType.EAGER)
    @Builder.Default
    private List<DetalleAportacion> detalleAportaciones = new ArrayList<>();

    @PreRemove
    public void setNullTipoAlimento() {
        detalleAportaciones.forEach(t -> t.setTipoAlimento(null));
    }

}
