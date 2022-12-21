package com.salesianostriana.dam.kiloapi.repos;

import com.salesianostriana.dam.kiloapi.dto.detalleaportacion.DetalleAportacionDto;
import com.salesianostriana.dam.kiloapi.model.KilosDisponibles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KilosDisponiblesRepository extends JpaRepository<KilosDisponibles, Long> {

    @Query("""
            SELECT NEW com.salesianostriana.dam.kiloapi.dto.detalleaportacion.DetalleAportacionDto(da.aportacion.id, da.id.numLinea, da.cantidadKilos)
            FROM DetalleAportacion da
            WHERE da.tipoAlimento.id = ?1
            """)
    List<DetalleAportacionDto> findDetallesOfKiloDisponible(Long idTipoAlimento);

}
