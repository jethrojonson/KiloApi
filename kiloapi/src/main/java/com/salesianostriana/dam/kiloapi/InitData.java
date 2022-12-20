package com.salesianostriana.dam.kiloapi;

import com.salesianostriana.dam.kiloapi.model.*;
import com.salesianostriana.dam.kiloapi.repos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData {

    private final AportacionRepository aportacionRepository;
    private final CajaRepository cajaRepository;
    private final ClaseRepository claseRepository;
    private final DestinatarioRepository destinatarioRepository;
    private final KilosDisponiblesRepository kilosDisponiblesRepository;
    private final TieneRepository tieneRepository;
    private final TipoAlimentoRepository tipoAlimentoRepository;

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

        tipoAlimentoRepository.saveAll(List.of(ta1, ta2, ta3));

        KilosDisponibles kd1 = KilosDisponibles.builder()
                .id(ta1.getId())
                .cantidadDisponible(5.9)
                .build();

        KilosDisponibles kd2 = KilosDisponibles.builder()
                .id(ta2.getId())
                .cantidadDisponible(2.9)
                .build();

        KilosDisponibles kd3 = KilosDisponibles.builder()
                .id(ta3.getId())
                .cantidadDisponible(1.9)
                .build();


        ta1.addToKilosDisponibles(kd1);
        ta2.addToKilosDisponibles(kd2);
        ta3.addToKilosDisponibles(kd3);

        tipoAlimentoRepository.saveAll(List.of(ta1, ta2, ta3));
        kilosDisponiblesRepository.saveAll(List.of(kd1, kd2, kd3));

        Aportacion a1 = Aportacion.builder()
                .fecha(LocalDate.of(2022, 12, 11))
                .clase(cl1)
                .build();

        DetalleAportacion da1 = DetalleAportacion.builder()
                .id(new DetalleAportacionPK(a1.getId(), 1L))
                .cantidadKilos(8.9)
                .aportacion(a1)
                .tipoAlimento(ta1)
                .build();

        DetalleAportacion da2 = DetalleAportacion.builder()
                .id(new DetalleAportacionPK(a1.getId(), 2L))
                .cantidadKilos(6)
                .aportacion(a1)
                .tipoAlimento(ta2)
                .build();

        DetalleAportacion da3 = DetalleAportacion.builder()
                .id(new DetalleAportacionPK(a1.getId(), 3L))
                .cantidadKilos(3.2)
                .aportacion(a1)
                .tipoAlimento(ta3)
                .build();

        a1.addDetalleAportacion(da1);
        a1.addDetalleAportacion(da2);
        a1.addDetalleAportacion(da3);

        aportacionRepository.save(a1);

        Destinatario d1 = Destinatario.builder()
                .nombre("Asociación Don Bosco")
                .direccion("Calle Juan Bosco")
                .personaContacto("Bosco")
                .telefono("678543234")
                .build();

        Destinatario d2 = Destinatario.builder()
                .nombre("Asociación MAría Auxiliadora")
                .direccion("Calle María Auxiliadora")
                .personaContacto("María")
                .telefono("675221094")
                .build();

        destinatarioRepository.saveAll(List.of(d1, d2));

        Caja c1 = Caja.builder()
                .qr("No existe")
                .numCaja(1)
                .kilosTotales(7.4)
                .destinatario(d1)
                .build();

        cajaRepository.saveAll(List.of(c1));

        Tiene t1 = Tiene.builder()
                .caja(c1)
                .tipoAlimento(ta1)
                .cantidadKgs(3)
                .build();
        Tiene t2 = Tiene.builder()
                .caja(c1)
                .tipoAlimento(ta2)
                .cantidadKgs(3.1)
                .build();
        Tiene t3 = Tiene.builder()
                .caja(c1)
                .tipoAlimento(ta3)
                .cantidadKgs(1.3)
                .build();

        tieneRepository.saveAll(List.of(t1, t2, t3));

    }

}
