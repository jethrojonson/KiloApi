package com.salesianostriana.dam.kiloapi.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class Aportacion {

    @Id @GeneratedValue
    private Long id;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "clase_id", foreignKey = @ForeignKey(name = "FK_APORTACION_CLASE"))
    private Clase clase;

    ///////////////////////////////////////
    /* HELPERS de la asociación con Clase*/
    ///////////////////////////////////////

    public void addToClase(Clase c) {
        this.clase = c;
        c.getAportaciones().add(this);
    }

    public void removeFromClase(Clase c) {
        this.clase = null;
        c.getAportaciones().remove(this);
    }

}
