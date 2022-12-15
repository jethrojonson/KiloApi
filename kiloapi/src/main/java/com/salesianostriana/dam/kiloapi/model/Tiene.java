package com.salesianostriana.dam.kiloapi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class Tiene {

    @EmbeddedId
    @Builder.Default
    private TienePK id = new TienePK();

    @ManyToOne
    @MapsId("tipo_alimento_id")
    @JoinColumn(name = "tipo_alimento_id")
    private TipoAlimento tipoAlimento;

    @ManyToOne
    @MapsId("caja_id")
    @JoinColumn(name = "caja_id")
    private Caja caja;

    private double cantidadKgs;

    ///////////////////////////////////////
    /* HELPERS de la asociación con Caja*/
    //////////////////////////////////////

    public void addToCaja(Caja c) {
        this.caja = c;
        c.getTieneList().add(this);
    }

    public void removeFromCaja(Caja c) {
        this.caja = null;
        c.getTieneList().remove(this);
    }


}
