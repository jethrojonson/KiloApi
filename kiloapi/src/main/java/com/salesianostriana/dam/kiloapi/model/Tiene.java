package com.salesianostriana.dam.kiloapi.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class Tiene {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tiene tiene = (Tiene) o;
        return id.equals(tiene.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

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

    //////////////////////////////////////////////
    /* HELPERS de la asociación con TipoAlimento*/
    //////////////////////////////////////////////

    public void addToTipoAlimento(TipoAlimento t){
        tipoAlimento = t;
        t.getTieneList().add(this);
    }

    public void removeFromTipoAlimento(TipoAlimento t){
        tipoAlimento = null;
        t.getTieneList().remove(this);
    }
/*
    @PreRemove
    public void setNull(){
        removeFromCaja(caja);
        removeFromTipoAlimento(tipoAlimento);


    }
*/

}
