# 🧩 Prueba Técnica - API de Registro de Usuarios con JWT y H2

Este proyecto es una API REST desarrollada con **Spring Boot 3**, que permite registrar usuarios, validar sus datos (email y contraseña), y generar un **token JWT** tras el registro.  
La aplicación utiliza **H2 Database** como base de datos en memoria y **Swagger UI** para documentación interactiva.

---

## Tecnologías utilizadas
- **Java 17**
- **Spring Boot 3.2.5**
- **Spring Data JPA**
- **H2 Database**
- **JWT (Json Web Token)**
- **Springdoc OpenAPI (Swagger UI)**
- **Gradle**

---

## Configuración del entorno

### Requisitos previos
- Java **17+**
- Gradle (o usar el wrapper `./gradlew`)
- IDE recomendado: IntelliJ IDEA o Spring Tools Suite

### Variables de entorno / propiedades
En el archivo `src/main/resources/application.properties` debe existir la siguiente propiedad para la clave JWT:


```properties
jwt.secret=my_super_secret_key_that_is_long_enough_to_be_valid
```
---

### Estructura del proyecto
```txt
demo-1/
│
├── README.md
├── docs/
│   └── diagrama-uml.png
└── src/main/java/com/example/demo
    ├── controller/
    │   └── UsuariosController.java
    │
    ├── dto/
    │   ├── request/
    │   │   ├── UserRequest.java
    │   │   └── PhoneRequest.java
    │   │
    │   └── response/
    │       └── UserResponse.java       
    │
    ├── model/
    │   ├── Users.java
    │   └── Phones.java
    │
    ├── repository/
    │   ├── UserRepository.java
    │   └── PhoneRepository.java
    │
    ├── service/
    │   └── UserServiceImpl.java
    │
    ├── util/
    │   └── JwtUtil.java
    │
    └── exception/
        ├── ErrorResponse.java
        └── UserCustomErrorException.java

```
---

## Base de datos H2

Este proyecto utiliza **H2 en memoria** junto con **Spring Data JPA**.  
No se requieren scripts SQL manuales, ya que las tablas se generan automáticamente 
al iniciar la aplicación según las entidades definidas en `model/`.

- **Consola H2:** http://localhost:8080/h2-console
- **Driver:** `org.h2.Driver`
- **JDBC URL:** `jdbc:h2:mem:UsuariosDB`
- **User:** `admin`
- **Password:** _(vacío)_

---

## Ejecución del proyecto

Sigue estos pasos para clonar, ejecutar y probar la API:

1. **Clonar el repositorio y cambiar a la rama MASTER con el código:**

```bash

git clone <URL_DEL_REPO>
git checkout master

```

2. **Abrir el proyecto en tu IDE**
   - Puedes usar Spring Tools Suite, IntelliJ IDEA, Eclipse u otro IDE compatible con Spring Boot.
   
3. **Ejecutar el comando**
```bash
  ./gradlew bootRun
```
- o desde el IDE, ejecutar la clase principal Application.java.

4. **Acceder a Swagger o usar Postman para probar los endpoints.**
---

## Endpoints 

### Registrar usuario

**POST** `/users/create`

#### Ejemplo de request:
```json
{
  "name": "Andrea",
  "email": "andra@gmail.cl",
  "password": "Andrea123",
  "phones": [
    {
      "number": "12345678",
      "citycode": "1",
      "contrycode": "56"
    }
  ]
}
```
#### Ejemplo de response exitoso:
```json
{
  "id": 1,
  "name": "Andrea",
  "email": "andrea@gmail.com",
  "created": "25-11-04 22:59:57",
  "modified": "25-11-04 22:59:57",
  "lastLogin": "25-11-04 22:59:57",
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmRyZWFAZ21haWwuY29tIiwiaWF0IjoxNzYyMzA3OTk4LCJleHAiOjE3NjIzNDM5OTh9.CST7JGUCOODfeE_1orAB2xnR4Tbq6eZPKW9Dniee--0",
  "active": true
}
```
#### Response en caso de error (email inválido):
```json
{
  "mensaje": "El Email [email] no es correcto."
}
```

#### Response en caso de error (email existente):
```json
{
  "mensaje": "El correo ya está registrado."
}
```

---



## Swagger UI

- Una vez que el proyecto esté corriendo, puedes acceder a la documentación interactiva en:
  👉 http://localhost:8080/swagger-ui/index.html
---

## Diagrama UML del proyecto

- ![Diagrama UML](docs/diagrama-uml.png)
---


## Funcionalidades implementadas

- Registro de usuarios

- Validación de formato de email

- Validación de emai existente

- Validación de contraseña segura

- Persistencia en base de datos H2

- Manejo de excepciones personalizadas

- Generación de token JWT

- Pruebas unitarias

- Documentación Swagger

- Diagrama de la solución
---


## 👩‍💻 Autor

Andrea Rojas Ruiz

Proyecto de Prueba Técnica — 2025
