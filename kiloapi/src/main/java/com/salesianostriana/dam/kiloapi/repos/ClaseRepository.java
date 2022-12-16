package com.salesianostriana.dam.kiloapi.repos;

import com.salesianostriana.dam.kiloapi.dto.clase.GetOneClaseDtoJ;
import com.salesianostriana.dam.kiloapi.model.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClaseRepository extends JpaRepository<Clase, Long> {

    @Query("SELECT sum(da.cantidadKilos) FROM DetalleAportacion da JOIN Aportacion a ON " +
            "(da.id.aportacion_id = a.id) WHERE a.clase.id = ?1")
    Double getCantidadKilos(Long idClase);



}
