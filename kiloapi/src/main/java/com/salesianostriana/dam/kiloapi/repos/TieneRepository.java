package com.salesianostriana.dam.kiloapi.repos;

import com.salesianostriana.dam.kiloapi.model.Tiene;
import com.salesianostriana.dam.kiloapi.model.TienePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TieneRepository extends JpaRepository<Tiene, TienePK> {
}
