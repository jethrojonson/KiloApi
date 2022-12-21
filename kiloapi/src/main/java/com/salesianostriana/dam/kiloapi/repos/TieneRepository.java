package com.salesianostriana.dam.kiloapi.repos;

import com.salesianostriana.dam.kiloapi.model.Tiene;
import com.salesianostriana.dam.kiloapi.model.TienePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TieneRepository extends JpaRepository<Tiene, TienePK> {

    @Query("""
            SELECT ti
            FROM Tiene ti
            WHERE ti.caja.id =?1
            AND ti.tipoAlimento.id =?2
            """)
    Tiene findOneTiene(Long idTipo, Long idCaja);
}
