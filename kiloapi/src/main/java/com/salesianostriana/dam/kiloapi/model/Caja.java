package com.salesianostriana.dam.kiloapi.model;

import lombok.*;

import javax.persistence.*;

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
    private String nombre,direccion,personaContacto,telefono;

    @ManyToOne
    @JoinColumn(name = "destinatario_id", foreignKey = @ForeignKey(name = "FK_CAJA_DESTINATARIO"))
    private Destinatario destinatario;

    public void addToDestinatario(Destinatario d) {
        this.destinatario= d;
        d.getCajas().add(this);
    }

    public void removeFromDestinatario(Destinatario d) {
        this.destinatario= null;
        d.getCajas().remove(this);
    }

}
