package com.salesianostriana.dam.kiloapi.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Builder.Default
    @OneToMany(
            mappedBy = "aportacion",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name="aportacion_id")
    private List<DetalleAportacion> detalles = new ArrayList<>();

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
