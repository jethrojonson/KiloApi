package com.salesianostriana.dam.kiloapi.repos;

import com.salesianostriana.dam.kiloapi.model.TipoAlimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TipoAlimentoRepository extends JpaRepository<TipoAlimento, Long> {

    @Query("SELECT t.tipoAlimento.id FROM Tiene t WHERE t.tipoAlimento.id=:id")
    TipoAlimento findTipoAlimentoOnTiene(Long id);

    @Query("SELECT da.tipoAlimento.id FROM DetalleAportacion da WHERE da.tipoAlimento.id=:id")
    TipoAlimento findTipoAlimentoOnDetallesAportacion(Long id);
}
