package com.salesianostriana.dam.kiloapi.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class Clase {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre, tutor;

    @OneToMany(mappedBy = "clase", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Aportacion> aportaciones = new ArrayList<>();

    @PreRemove
    public void setNullClases() {
        aportaciones.forEach(a -> a.setClase(null));
    }

}
