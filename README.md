# 🧩 Prueba Técnica - API de Registro de Usuarios con JWT y H2

Este proyecto es una API REST desarrollada con **Spring Boot 3**, que permite registrar usuarios, validar sus datos (email y contraseña), y generar un **token JWT** tras el registro.  
La aplicación utiliza **H2 Database** como base de datos en memoria y **Swagger UI** para documentación interactiva.

---

## 🚀 Tecnologías utilizadas
- **Java 17**
- **Spring Boot 3.2.5**
- **Spring Data JPA**
- **H2 Database**
- **JWT (Json Web Token)**
- **Springdoc OpenAPI (Swagger UI)**
- **Gradle**

---

## ⚙️ Configuración del entorno

### Requisitos previos
- Java **17+**
- Gradle (o usar el wrapper `./gradlew`)
- IDE recomendado: IntelliJ IDEA o Spring Tools Suite

### Variables de entorno / propiedades
En el archivo `src/main/resources/application.properties` debe existir la siguiente propiedad para la clave JWT:


```properties
jwt.secret=my_super_secret_key_that_is_long_enough_to_be_valid
```

### 🧠 Estructura del proyecto
```txt
src/main/java/com/example/
│
├── controller/
│   └── UsuariosController.java
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
│   └── UserService.java
│
├── security/
│   └── JwtUtil.java
│
└── exception/
    └── UserCustomErrorException.java
```
---

## 💾 Base de datos H2

- **Consola H2:** http://localhost:8080/h2-console
- **Driver:** `org.h2.Driver`
- **JDBC URL:** `jdbc:h2:mem:UsuariosDB`
- **User:** `admin`
- **Password:** _(vacío)_

---

## 📘 Endpoints principales

### 1️⃣ Registrar usuario

**POST** `/users/create`

#### Ejemplo de request:
```json
{
  "name": "Andrea",
  "email": "andra@gmail.cl",
  "password": "Andrea123",
  "phones": [
    {
      "phone": "12345678",
      "citycode": "1",
      "contrycode": "56"
    }
  ]
}
```
#### Ejemplo de response exitoso:
```json
{
  "name": "Andrea",
  "email": "andra@gmail.cl",
  "password": "Andrea123",
  "phones": [
    {
      "phone": "12345678",
      "citycode": "1",
      "contrycode": "56"
    }
  ]
}
```
#### Response en caso de error (email inválido):
```json
{
  "mensaje": "El Email andra.cl no es correcto."
}
```

### 2️⃣ Generar token manualmente

**GET** `/users/generateToken?username=usuario@correo.com`  
- Devuelve un token JWT válido por 10 horas.

### 3️⃣ Simular error personalizado

**GET** `/users/error`
- Devuelve una excepción personalizada con mensaje definido en la API.

---

## 🧭 Swagger UI

- Una vez que el proyecto esté corriendo, puedes acceder a la documentación interactiva en:
👉 http://localhost:8080/swagger-ui/index.html
---

## 🧪 Ejecución del proyecto

- Clonar o descargar el repositorio.

- Abrir el proyecto en tu IDE (Spring Tools Suite, IntelliJ, etc.).

- Ejecutar el comando:
```bash
  ./gradlew bootRun
```
o desde el IDE, ejecutar la clase principal Application.java.

- Acceder a Swagger o usar Postman para probar los endpoints.
---

## ✅ Funcionalidades implementadas

- Registro de usuarios

- Validación de formato de email

- Validación de contraseña segura

- Persistencia en base de datos H2

- Generación de token JWT

- Documentación Swagger

- Manejo de excepciones personalizadas

- Validación de token en endpoints protegidos (opcional)
---

## 🧩 Tecnologías utilizadas

- Java 17

- Spring Boot 3.2.5

- Gradle

- H2 Database

- JWT (io.jsonwebtoken)

- Springdoc OpenAPI (Swagger UI)

- Lombok
---

## 👩‍💻 Autor

Andrea Rojas Ruiz
Proyecto de Prueba Técnica — 2025
