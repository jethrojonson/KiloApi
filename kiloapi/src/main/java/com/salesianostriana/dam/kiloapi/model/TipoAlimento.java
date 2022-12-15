package com.salesianostriana.dam.kiloapi.model;

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

    @OneToOne(mappedBy = "tipoAlimento", fetch = FetchType.EAGER)
    private KilosDisponibles kilosDisponibles;

    @OneToMany(mappedBy = "tipoAlimento", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Tiene> tieneList = new ArrayList<>();

    //////////////////////////////////////////////////
    /* HELPERS de la asociación con KilosDisponibles*/
    //////////////////////////////////////////////////

    public void addToKilosDisponibles(KilosDisponibles kd) {
        this.kilosDisponibles = kd;
        kd.setTipoAlimento(this);
    }

    public void removeFromKilosDisponible(KilosDisponibles kd) {
        this.kilosDisponibles = null;
        kd.setTipoAlimento(null);
    }


}