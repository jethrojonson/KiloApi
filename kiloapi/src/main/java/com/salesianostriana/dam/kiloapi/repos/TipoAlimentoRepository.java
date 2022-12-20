package com.salesianostriana.dam.kiloapi.repos;

import com.salesianostriana.dam.kiloapi.model.TipoAlimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TipoAlimentoRepository extends JpaRepository<TipoAlimento, Long> {

    TipoAlimento findTipoAlimentoById(Long id);


    //query
    //@Query
            //("SELECT t.tipoAlimento.id FROM Tiene t WHERE t.tipoAlimento.id=:id")

    //y lo mismo con detalle aportacion
}
