package com.salesianostriana.dam.kiloapi.repos;

import com.salesianostriana.dam.kiloapi.dto.kilosdisponibles.GetDetallesKilosDisponiblesDto;
import com.salesianostriana.dam.kiloapi.model.KilosDisponibles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KilosDisponiblesRepository extends JpaRepository<KilosDisponibles, Long> {

    @Query("""
            SELECT NEW com.salesianostriana.dam.kiloapi.dto.kilosdisponibles.GetDetallesKilosDisponiblesDto(da.aportacion.id, da.id.numLinea, da.cantidadKilos)
            FROM DetalleAportacion da
            WHERE da.tipoAlimento.id = ?1
            """)
    List<GetDetallesKilosDisponiblesDto> findDetallesOfKiloDisponible(Long idTipoAlimento);

}
