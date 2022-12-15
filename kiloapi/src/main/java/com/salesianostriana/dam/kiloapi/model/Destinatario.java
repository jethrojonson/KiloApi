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
public class Destinatario {

    @Id
    @GeneratedValue
    private Long id;
    private String qr;
    private int numCaja;
    private double kilosTotales;

    @OneToMany(mappedBy = "destinatario", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Caja> cajas = new ArrayList<>();

    @PreRemove
    public void setNullDestinatarios() {
        cajas.forEach(d -> d.setDestinatario(null));
    }

}
