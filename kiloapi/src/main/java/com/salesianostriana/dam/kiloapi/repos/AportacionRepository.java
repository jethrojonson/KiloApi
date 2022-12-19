package com.salesianostriana.dam.kiloapi.repos;

import com.salesianostriana.dam.kiloapi.dto.aportacion.GetAportacionQueryDto;
import com.salesianostriana.dam.kiloapi.dto.aportacion.GetNewAportacionDto;
import com.salesianostriana.dam.kiloapi.model.Aportacion;
import com.salesianostriana.dam.kiloapi.model.DetalleAportacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AportacionRepository extends JpaRepository<Aportacion, Long> {

    //SELECT * FROM APORTACION ORDER BY ID DESC LIMIT 1
    Aportacion findFirstByOrderByIdDesc();

    /*
    SELECT TA.NOMBRE, DA.CANTIDAD_KILOS
    FROM APORTACION A JOIN DETALLE_APORTACION DA ON A.ID = DA.APORTACION_ID
    JOIN TIPO_ALIMENTO TA ON DA.TIPO_ALIMENTO_ID = TA.ID
    WHERE CLASE_ID = ?
     */
    @Query("""
            SELECT NEW com.salesianostriana.dam.kiloapi.dto.aportacion.GetAportacionQueryDto(da.tipoAlimento.nombre, da.cantidadKilos)
            FROM DetalleAportacion da
            WHERE da.aportacion.clase.id = ?1
            """)
    List<GetAportacionQueryDto> getListOfNamesAmount(Long id);

    @Query("""
            SELECT da
            FROM DetalleAportacion da
            WHERE da.aportacion.id = ?1
            AND da.id.numLinea = ?2
            """)
    DetalleAportacion findOneDetalleAportacion(Long idAportacion, Long numLinea);

    @Query("""
            SELECT SUM(da.cantidadKilos)
            FROM DetalleAportacion da
            WHERE da.tipoAlimento.id = ?1
            """)
    Double findAllKilosOfATipoAlimento(Long idTipoAlimento);
}
