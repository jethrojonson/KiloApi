package com.salesianostriana.dam.kiloapi;

import com.salesianostriana.dam.kiloapi.model.*;
import com.salesianostriana.dam.kiloapi.repos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData {

    private final AportacionRepository aportacionRepository;
    private final CajaRepository cajaRepository;
    private final ClaseRepository claseRepository;
    private final DestinatarioRepository destinatarioRepository;
    private final DetalleAportacionRepository detalleAportacionRepository;
    private final KilosDisponiblesRepository kilosDisponiblesRepository;
    private final TieneRepository tieneRepository;
    private final TipoAlimentoRepository tipoAlimentoRepo;

    @PostConstruct
    public void run() {

        Clase cl1 = Clase.builder()
                .nombre("2ª DAM")
                .tutor("Luismi")
                .build();

        Clase cl2 = Clase.builder()
                .nombre("1ºDAM")
                .tutor("Eduardo")
                .build();

        claseRepository.saveAll(List.of(cl1, cl2));

        TipoAlimento ta1 = TipoAlimento.builder()
                .nombre("Garbanzos")
                .build();

        TipoAlimento ta2 = TipoAlimento.builder()
                .nombre("Dodotis")
                .build();

        TipoAlimento ta3 = TipoAlimento.builder()
                .nombre("Lentejas")
                .build();

        tipoAlimentoRepo.saveAll(List.of(ta1, ta2, ta3));



        KilosDisponibles kd1 = KilosDisponibles.builder()
                .id(ta1.getId())
                .cantidadDisponible(13.5)
                .build();

        KilosDisponibles kd2 = KilosDisponibles.builder()
                .id(ta2.getId())
                .cantidadDisponible(4)
                .build();

        KilosDisponibles kd3 = KilosDisponibles.builder()
                .id(ta3.getId())
                .cantidadDisponible(6.8)
                .build();


        ta1.addToKilosDisponibles(kd1);
        ta2.addToKilosDisponibles(kd2);
        ta3.addToKilosDisponibles(kd3);

        tipoAlimentoRepo.saveAll(List.of(ta1, ta2, ta3));
        kilosDisponiblesRepository.saveAll(List.of(kd1, kd2, kd3));


        Destinatario d1 = Destinatario.builder()
                .nombre("Asociación Don Bosco")
                .direccion("Calle Tu Casa")
                .personaContacto("Bosco")
                .telefono("678543234")
                .build();

        Destinatario d2 = Destinatario.builder()
                .nombre("Asociación MAría Auxiliadora")
                .direccion("Calle Pepito")
                .personaContacto("María")
                .telefono("675221094")
                .build();

        destinatarioRepository.saveAll(List.of(d1, d2));

        Caja c1 = Caja.builder()
                .qr("https://stackoverflow.com/questions/35982162/json-output-of-entity-with-onetoone-relationship")
                .numCaja(1)
                .kilosTotales(23.2)
                .destinatario(d1)
                .build();

        Caja c2 = Caja.builder()
                .qr("https://stackoverflow.com/questions/35982162/json-output-of-entity-with-onetoone-relationship")
                .numCaja(2)
                .kilosTotales(15.6)
                .destinatario(d2)
                .build();

        cajaRepository.saveAll(List.of(c1, c2));

        Tiene t1 = Tiene.builder()
                .caja(c1)
                .tipoAlimento(ta1)
                .cantidadKgs(12.6)
                .build();

        tieneRepository.save(t1);


        //nico //intentando crear una aportacion
//        Aportacion a = Aportacion.builder()
//                .fecha(LocalDate.of(2022,8,12))
//                .clase(cl1)
//                .detalles(
//                        List.of(
//                                DetalleAportacion.builder()
//                                        .aportacion(this)
//                                        .build()
//                        )
//                )
//                .build();


    }

}
