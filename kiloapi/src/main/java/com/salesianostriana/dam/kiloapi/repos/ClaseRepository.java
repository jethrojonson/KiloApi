package com.salesianostriana.dam.kiloapi.repos;

import com.salesianostriana.dam.kiloapi.dto.clase.GetOneClaseDtoJ;
import com.salesianostriana.dam.kiloapi.model.Clase;
import com.salesianostriana.dam.kiloapi.dto.ranking.GetRankingQueryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClaseRepository extends JpaRepository<Clase, Long> {

    @Query("SELECT sum(da.cantidadKilos) FROM DetalleAportacion da JOIN Aportacion a ON " +
            "(da.id.aportacion_id = a.id) WHERE a.clase.id = ?1")
    Double getCantidadKilos(Long idClase);

    @Query("SELECT new com.salesianostriana.dam.kiloapi.dto.clase.GetOneClaseDtoJ" +
            "(c.id, c.nombre, c.tutor, CAST(count(da.id.aportacion_id)AS int), sum(da.cantidadKilos)) FROM DetalleAportacion da JOIN Aportacion" +
            " a ON (da.id.aportacion_id = a.id) JOIN Clase c ON (a.id = c.id) WHERE c.id = ?1 GROUP BY c.id")
    GetOneClaseDtoJ getCntKilos(Long idClase);

    /*
    SELECT C.ID, C.NOMBRE, SUM(DA.CANTIDAD_KILOS)
    FROM CLASE C JOIN APORTACION A ON C.ID = A.CLASE_ID JOIN DETALLE_APORTACION DA ON A.ID = DA.APORTACION_ID
    GROUP BY C.ID
    ORDER BY SUM(DA.CANTIDAD_KILOS) DESC;
     */
    @Query("""
            SELECT DISTINCT NEW com.salesianostriana.dam.kiloapi.dto.ranking.GetRankingQueryDto(c.id, c.nombre, SUM(da.cantidadKilos))
            FROM Clase c JOIN Aportacion a ON c.id = a.clase.id JOIN DetalleAportacion da ON a.id = da.aportacion.id
            GROUP BY c.id
            ORDER BY SUM(da.cantidadKilos) DESC
            """)
    List<GetRankingQueryDto> getRankingQueryDto();

    /*
    SELECT C.ID, C.NOMBRE, COUNT(DA.APORTACION_ID), SUM(DA.CANTIDAD_KILOS),  AVG(
    SELECT DISTINCT SUM(DA2.CANTIDAD_KILOS)
    FROM APORTACION A2 JOIN DETALLE_APORTACION DA2 ON A2.ID = DA2.APORTACION_ID
    GROUP BY DA2.APORTACION_ID
    ) AS "MEDIA"
    FROM CLASE C JOIN APORTACION A ON C.ID = A.CLASE_ID JOIN DETALLE_APORTACION DA ON A.ID = DA.APORTACION_ID
    GROUP BY C.ID
    ORDER BY SUM(DA.CANTIDAD_KILOS) DESC;

    @Query("""
            SELECT DISTINCT NEW com.salesianostriana.dam.kiloapi.dto.ranking.GetRankingQueryDto(c.id, c.nombre, COUNT(da.aportacion.id), sum(da.cantidadKilos), avg(
                SELECT DISTINCT sum(da2.cantidadKilos)
                FROM Aportacion a2 JOIN DestalleAportacion da2 ON a2.id = da2.aportacion.id
                GROUP BY da2.aportacion.id
            ))
            FROM Clase c JOIN Aportacion a ON c.id = a.clase.id JOIN DetalleAportacion da ON a.id = da.aportacion.id
            GROUP BY c.id
            ORDER BY sum(da.cantidadKilos) DESC;
            """)


     */

}
