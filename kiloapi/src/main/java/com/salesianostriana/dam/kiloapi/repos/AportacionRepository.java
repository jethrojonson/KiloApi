package com.salesianostriana.dam.kiloapi.repos;

import com.salesianostriana.dam.kiloapi.model.Aportacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AportacionRepository extends JpaRepository<Aportacion, Long> {

    //SELECT * FROM APORTACION ORDER BY ID DESC LIMIT 1

    Aportacion findFirstByOrderByIdDesc();

}
