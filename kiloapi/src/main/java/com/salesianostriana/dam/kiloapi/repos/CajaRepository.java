package com.salesianostriana.dam.kiloapi.repos;

import com.salesianostriana.dam.kiloapi.dto.caja.CajaTipoAlimentoDto;
import com.salesianostriana.dam.kiloapi.model.Caja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CajaRepository extends JpaRepository<Caja,Long> {

    @Query("""
            SELECT NEW com.salesianostriana.dam.kiloapi.dto.caja.CajaTipoAlimentoDto(t.tipoAlimento.id, t.tipoAlimento.nombre, t.cantidadKgs)
            FROM Tiene t
            WHERE t.caja.id = ?1
            """)
    List<CajaTipoAlimentoDto> findAlimentosOfACaja(Long idCaja);

}
