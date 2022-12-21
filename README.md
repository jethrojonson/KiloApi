# KiloApi

<p align="center">
  <img src="https://img.shields.io/badge/STATUS-FINISH-red" alt="Status del proyecto"/>
  <a href="https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html"><img src="https://img.shields.io/badge/jdk-v17.0.4.1-blue" alt="Versión java" /></a>
  <a href="https://maven.apache.org/download.cgi"><img src="https://img.shields.io/badge/apache--maven-v3.8.6-blue" alt="Versión maven" /></a>
  <a href="https://spring.io/projects/spring-boot"><img src="https://img.shields.io/badge/spring--boot-v2.7.6-blue" alt="Versión spring-boot" /></a>
  <img src="https://img.shields.io/badge/release%20date-december-yellowgreen" alt="Lanzamiento del proyecto" />
  <img src="https://img.shields.io/badge/license-MIT-brightgreen" alt="Licencia" />
</p>

## Descripción
**KiloApi** es una aplicación desarrollada con Spring Boot 2.7.6 que nos permite gestionar la operación kilo
que se realiza en los Salesianos de Triana

## Autores
#### José Luis Gil 
#### Víctor González
#### Jerónimo Manuel Pérez 
#### Nicolás Fernández

### Entidades

* #### Clase
  - Clase (entity/model)
  - ClaseService
  - ClaseRepository
  - ClaseController
  - ClaseDTOs

* #### Aportacion
  - Aportacion (entity/model)
  - AportacionService
  - AportacionRepository
  - AportacionController
  - AportacionDTOs

* #### DetalleAportacion
  - DetalleAportacion (entitiy/model)
  - DetalleAportacionDTO

* #### TipoAlimento
  - TipoAlimento (entity/model)
  - TipoAlimentoService
  - TipoAlimentoRepository
  - TipoAlimentoController
  - TipoAlimentoDTOs

* #### KilosDisponibles
  - KilosDisponibles (entity/model)
  - KilosDisponiblesService
  - KilosDisponiblesRepository
  - KilosDisponiblesController
  - KilosDisponiblesDTOs

* #### Tiene
  - Tiene (entity/model)
  - TieneService
  - TieneRepository

* #### Caja
  - Caja (entity/model)
  - CajaService
  - CajaRepository
  - CajaController
  - CajaDTOs

* #### Destinatario
  - Destinatario (entity/model)
  - DestinatarioService
  - DestinatarioRepository
  - DestinatarioController
  - DestinatarioDTOs

* #### Ranking
  - RankingController
<br/>

## :hammer:Funcionalidades

### Funcionalidades de Clase

1. Añadir una clase
2. Obtener todas las clases
3. Obtener una clase por su identificador
4. Editar una clase por su identificador
5. Borrar una clase por su identificador

### Funcionalidades de Aportación

1. Añadir una aportación
2. Obtener todas las aportaciones
3. Obtener una aportación por su identificador
4. Editar ula cantidad de kg de una aportación por su identificador
5. Borrar una aportación por su identificador
6. Obtener las aportaciones de una clase
7. Borrar un detalle de aportación de una aportación

### Funcionalidades de TipoAlimento

1. Añadir un tipo de alimento
2. Obtener todos los tipos de alimentos
3. Obtener un tipo de alimento por su identificador
4. Editar un tipo de alimento por su identificador
5. Borrar un tipo de alimento por su identificador

### Funcionalidades de KilosDisponibles

1. Obtener todos los kilos disponibles
2. Obtener los kilos disponibles de un tipo de alimento

### Funcionalidades de Ranking

1. Obtener el ranking de las clases según la cantidad de kilos que han aportado

### Funcionalidades de Destinatario

1. Añadir un destinatario
2. Obtener todos los destinatarios
3. Obtener un destinatario por su identificador
4. Editar un destinatario por su identificador
5. Borrar un destinatario por su identificador
6. Obtener los detalles de un destinatario

### Funcionalidades de Caja

1. Añadir una caja
2. Obtener todas las cajas
3. Obtener una caja por su identificador
4. Editar una caja por su identificador
5. Borrar una caja por su identificador
6. Añadir una cantidad de kg de un tipo de alimento a una caja
7. Editar una cantidad de kg de un tipo de alimento a una caja
8. Borrar un tipo de alimento de una caja
9. Añadir un destinatario a una caja

## Arrancar el proyecto

* Descargar IntelliJ IDEA Community Edition
* Descargar Java jdk 17
* Descargar apache-maven 3.8.6
* Configurar la versón de Java dentro de IntelliJ
* Clicar en el botón *Edit Configurations*
* Añadir una nueva configuración de tipo **Maven**
* En el apartado de *Run* escribir **spring-boot:run**