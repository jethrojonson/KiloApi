package com.salesianostriana.dam.kiloapi.repos;

import com.salesianostriana.dam.kiloapi.dto.destinatario.ListaDetallesCajaDto;
import com.salesianostriana.dam.kiloapi.dto.destinatario.ListaTipoAlimentoDto;
import com.salesianostriana.dam.kiloapi.model.Destinatario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DestinatarioRepository extends JpaRepository<Destinatario,Long> {

    @Query("""
            SELECT NEW com.salesianostriana.dam.kiloapi.dto.destinatario.ListaTipoAlimentoDto(t.tipoAlimento.nombre, t.cantidadKgs)
            FROM Tiene t
            WHERE t.caja.destinatario.id = ?1
            """)
    List<ListaTipoAlimentoDto> findNombreTipoAlimentoYCantidadKgsDeUnDestinatario(Long idDestinatario);
    @Query("""
            SELECT NEW com.salesianostriana.dam.kiloapi.dto.destinatario.ListaDetallesCajaDto(c.numCaja, c.kilosTotales)
            FROM Caja c
            WHERE c.destinatario.id = ?1
            """)
    List<ListaDetallesCajaDto> findNumCajaYKgsTotalesDeUnDestinatario(Long idDestinatario);

}
