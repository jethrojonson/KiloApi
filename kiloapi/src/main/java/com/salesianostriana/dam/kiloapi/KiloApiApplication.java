package com.salesianostriana.dam.kiloapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =
		@Info(description = "API que gestiona la Operación Kilo de los Salesianos de San Pedro",
				version = "1.0",
				contact = @Contact(email = "grupo6@triana.salesianos.edu", name = "Grupo 6 (José Luis Gil, Víctor González, Jerónimo Pérez, Nicolás Fernández)"),
				license = @License(name = "Salesianos Triana"),
				title = "KiloAPI"
		)
)
public class KiloApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KiloApiApplication.class, args);
	}

}
