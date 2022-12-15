package com.salesianostriana.dam.kiloapi.repos;

import com.salesianostriana.dam.kiloapi.model.TipoAlimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoAlimentoRepository extends JpaRepository<TipoAlimento, Long> {

    TipoAlimento findTipoAlimentoById(Long id);

}
