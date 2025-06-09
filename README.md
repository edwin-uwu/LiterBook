Desafío Técnico LiterBook: Gestión de Libros

- Visión General del Proyecto

Este proyecto es una aplicación backend desarrollada en Java con Spring Boot, diseñada para gestionar y consultar información sobre libros y autores. La aplicación interactúa con la API externa de Gutendex para obtener datos iniciales de libros y persiste esta información en una base de datos PostgreSQL. Las funcionalidades posteriores se realizan directamente sobre la base de datos local, demostrando capacidades de almacenamiento y consulta eficientes.

- Características Principales

La aplicación ofrece un menú interactivo en consola con las siguientes opciones:

Buscar libro por título: Permite buscar un libro por su título en la API de Gutendex. Una vez encontrado, el libro y sus autores/idiomas asociados se guardan en la base de datos local.

Lista de libros: Muestra todos los libros almacenados en la base de datos local.

Listar autores: Muestra todos los autores almacenados en la base de datos local.

Listar autores vivos en determinada fecha: Permite consultar autores que estaban vivos en un año específico o en un rango de años, basándose en su fecha de nacimiento y muerte.

Listar libros por idioma: Muestra los libros disponibles en un idioma específico, consultando la base de datos local.

Salir: Finaliza la ejecución de la aplicación.

🛠️ Tecnologías Utilizadas

Java 17: Lenguaje de programación.

Spring Boot 3.5.0: Framework para el desarrollo rápido de aplicaciones.

Maven: Herramienta de gestión de dependencias y construcción de proyectos.

Spring Data JPA: Para la persistencia de datos y la interacción con la base de datos.

PostgreSQL: Sistema de gestión de base de datos relacional.

Jackson: Para la serialización y deserialización de JSON (manejo de las respuestas de la API de Gutendex).

📦 Dependencias del Proyecto

Las dependencias utilizadas se configuran en el archivo pom.xml:

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.19.0</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-annotations</artifactId>
        <version>2.19.0</version>
    </dependency>
</dependencies>


⚙️ Configuración y Variables de Entorno

La aplicación se conecta a una base de datos PostgreSQL. Las credenciales de conexión se gestionan a través de variables de entorno para mayor seguridad y flexibilidad.

Asegúrate de configurar las siguientes variables de entorno en tu sistema o en el entorno de ejecución:

DB_HOST: El host de tu base de datos PostgreSQL (ej. localhost, tu_servidor_bd).

DB_USER: El nombre de usuario para acceder a la base de datos.

DB_PASSWORD: La contraseña para el usuario de la base de datos.

La configuración en application.properties (o application.yml) es la siguiente:

spring.datasource.url=jdbc:postgresql://${DB_HOST}/literbook
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect # Corregido de HSQLDialect a PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update # O 'create' si quieres que recree el esquema en cada inicio
