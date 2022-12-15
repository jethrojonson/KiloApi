package com.salesianostriana.dam.kiloapi.repos;

import com.salesianostriana.dam.kiloapi.model.DetalleAportacion;
import com.salesianostriana.dam.kiloapi.model.DetalleAportacionPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleAportacionRepository extends JpaRepository<DetalleAportacion, DetalleAportacionPK> {
}
