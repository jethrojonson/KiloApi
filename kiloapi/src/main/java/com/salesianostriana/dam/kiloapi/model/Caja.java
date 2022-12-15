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
public class Caja {

    @Id
    @GeneratedValue
    private Long id;

    private String qr;
    private int numCaja;
    private double kilosTotales;

    @ManyToOne
    @JoinColumn(name = "destinatario_id", foreignKey = @ForeignKey(name = "FK_CAJA_DESTINATARIO"))
    private Destinatario destinatario;

    @OneToMany(mappedBy = "caja", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Tiene> tieneList = new ArrayList<>();

    public void addToDestinatario(Destinatario d) {
        this.destinatario= d;
        d.getCajas().add(this);
    }

    public void removeFromDestinatario(Destinatario d) {
        this.destinatario= null;
        d.getCajas().remove(this);
    }

}
