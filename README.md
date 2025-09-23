# SebaTech API 🚀

Una API REST desarrollada con Spring Boot para la gestión de una tienda de productos de PC. Este proyecto proporciona endpoints para administrar usuarios, roles, categorías y marcas de productos.

## 📋 Tabla de Contenidos

- [Características](#características)
- [Tecnologías](#tecnologías)
- [Requisitos Previos](#requisitos-previos)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Uso](#uso)
- [Endpoints de la API](#endpoints-de-la-api)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Base de Datos](#base-de-datos)
- [Contribuir](#contribuir)

## ✨ Características

- **Gestión de Usuarios**: CRUD completo para usuarios con autenticación por roles
- **Gestión de Roles**: Administración de roles de usuario
- **Gestión de Categorías**: Organización de productos por categorías
- **Gestión de Marcas**: Administración de marcas de productos
- **Validación de Datos**: Validación robusta usando Jakarta Bean Validation
- **Manejo Global de Excepciones**: Respuestas de error consistentes y estructuradas
- **Arquitectura Limpia**: Separación clara entre capas (Controller, Service, Repository, DTO)
- **Mapeo de Entidades**: Transformación automática entre entidades y DTOs

## 🛠 Tecnologías

- **Java 21**
- **Spring Boot 3.5.4**
- **Spring Data JPA**
- **Spring Web**
- **Spring Boot Validation**
- **PostgreSQL**
- **Lombok**
- **Maven 3.9.11**

## 📋 Requisitos Previos

Antes de ejecutar este proyecto, asegúrate de tener instalado:

- Java 21 o superior
- Maven 3.6+
- PostgreSQL 12+
- Un IDE como IntelliJ IDEA, VS Code, o Eclipse

## 🔧 Instalación

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/xsebastv/sebastech_api.git
   cd sebastech_api
   ```

2. **Configura la base de datos PostgreSQL:**
   - Crea una base de datos llamada `sebastech_db`
   - Configura las credenciales en `application.properties`

3. **Instala las dependencias:**
   ```bash
   ./mvnw clean install
   ```

4. **Ejecuta la aplicación:**
   ```bash
   ./mvnw spring-boot:run
   ```

La aplicación estará disponible en: `http://localhost:8080`

## ⚙️ Configuración

### Base de Datos

Edita el archivo `src/main/resources/application.properties`:

```properties
spring.application.name=sebastech
spring.datasource.url=jdbc:postgresql://localhost:5432/sebastech_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=org.postgresql.Driver

# Configuraciones adicionales de JPA (opcional)
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

## 📖 Uso

### Ejemplos de Peticiones

#### Crear un Usuario
```bash
curl -X POST http://localhost:8080/api/usuario \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Juan",
    "lastName": "Pérez",
    "email": "juan.perez@example.com",
    "phone": "1234567890",
    "password": "securePassword123",
    "roleId": 1
  }'
```

#### Obtener todos los Usuarios
```bash
curl -X GET http://localhost:8080/api/usuario/all/object
```

#### Crear una Categoría
```bash
curl -X POST http://localhost:8080/api/categoria \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Procesadores",
    "descripcion": "Procesadores para computadoras de escritorio y portátiles"
  }'
```

## 🌐 Endpoints de la API

### 👤 Usuarios (`/api/usuario`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/all` | Obtiene lista de nombres de usuarios |
| `GET` | `/all/object` | Obtiene todos los usuarios con detalles completos |
| `POST` | `/` | Crea un nuevo usuario |
| `GET` | `/{id}` | Obtiene un usuario por ID |
| `PUT` | `/{id}` | Actualiza un usuario existente |
| `DELETE` | `/{id}` | Elimina un usuario |

### 🔐 Roles (`/api/rol`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/all` | Obtiene lista de nombres de roles |
| `GET` | `/all/object` | Obtiene todos los roles con detalles completos |
| `POST` | `/` | Crea un nuevo rol |
| `GET` | `/{id}` | Obtiene un rol por ID |
| `PUT` | `/{id}` | Actualiza un rol existente |
| `DELETE` | `/{id}` | Elimina un rol |

### 📂 Categorías (`/api/categoria`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/all` | Obtiene lista de nombres de categorías |
| `GET` | `/all/object` | Obtiene todas las categorías con detalles completos |
| `POST` | `/` | Crea una nueva categoría |
| `GET` | `/{id}` | Obtiene una categoría por ID |
| `PUT` | `/{id}` | Actualiza una categoría existente |
| `DELETE` | `/{id}` | Elimina una categoría |

### 🏷️ Marcas (`/api/marca`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/all` | Obtiene lista de nombres de marcas |
| `GET` | `/all/object` | Obtiene todas las marcas con detalles completos |
| `POST` | `/` | Crea una nueva marca |
| `GET` | `/{id}` | Obtiene una marca por ID |
| `PUT` | `/{id}` | Actualiza una marca existente |
| `DELETE` | `/{id}` | Elimina una marca |

## 📁 Estructura del Proyecto

```
sebastech_api/
├── src/
│   ├── main/
│   │   ├── java/co/edu/usbcali/sebastech/
│   │   │   ├── SebastechApplication.java          # Clase principal
│   │   │   ├── controller/                        # Controladores REST
│   │   │   │   ├── UsuarioController.java
│   │   │   │   ├── RolController.java
│   │   │   │   ├── CategoriaController.java
│   │   │   │   └── MarcaController.java
│   │   │   ├── service/                           # Lógica de negocio
│   │   │   │   ├── UsuarioService.java
│   │   │   │   ├── UsuarioServiceImpl.java
│   │   │   │   ├── RolService.java
│   │   │   │   ├── RolServiceImpl.java
│   │   │   │   ├── CategoriaService.java
│   │   │   │   ├── CategoriaServiceImpl.java
│   │   │   │   ├── MarcaService.java
│   │   │   │   └── MarcaServiceImpl.java
│   │   │   ├── repository/                        # Acceso a datos
│   │   │   │   ├── UsuarioRepository.java
│   │   │   │   ├── RolRepository.java
│   │   │   │   ├── CategoriaRepository.java
│   │   │   │   └── MarcaRepository.java
│   │   │   ├── domain/                            # Entidades JPA
│   │   │   │   ├── Usuario.java
│   │   │   │   ├── Rol.java
│   │   │   │   ├── Categoria.java
│   │   │   │   └── Marca.java
│   │   │   ├── dto/                               # Objetos de transferencia
│   │   │   │   ├── UsuarioRequestDTO.java
│   │   │   │   ├── UsuarioResponseDTO.java
│   │   │   │   ├── RolRequestDTO.java
│   │   │   │   ├── RolResponseDTO.java
│   │   │   │   ├── CategoriaRequestDTO.java
│   │   │   │   ├── CategoriaResponseDTO.java
│   │   │   │   ├── MarcaRequestDTO.java
│   │   │   │   ├── MarcaResponseDTO.java
│   │   │   │   └── MessageResponse.java
│   │   │   ├── mapper/                            # Mapeo entre entidades y DTOs
│   │   │   │   ├── UsuarioMapper.java
│   │   │   │   ├── RolMapper.java
│   │   │   │   ├── CategoriaMapper.java
│   │   │   │   └── MarcaMapper.java
│   │   │   └── exception/                         # Manejo de excepciones
│   │   │       ├── GlobalExceptionHandler.java
│   │   │       ├── ApiError.java
│   │   │       ├── BadRequestException.java
│   │   │       ├── ConflictException.java
│   │   │       └── NotFoundException.java
│   │   └── resources/
│   │       └── application.properties             # Configuración de la aplicación
│   └── test/
│       └── java/co/edu/usbcali/sebastech/
│           └── SebastechApplicationTests.java     # Pruebas unitarias
├── pom.xml                                        # Configuración de Maven
├── mvnw                                           # Maven Wrapper (Unix)
├── mvnw.cmd                                       # Maven Wrapper (Windows)
└── README.md                                      # Este archivo
```

## 🗃️ Base de Datos

### Esquema de Tablas

#### Tabla `roles`
```sql
CREATE TABLE roles (
    id_rol SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion TEXT
);
```

#### Tabla `usuarios`
```sql
CREATE TABLE usuarios (
    id_usuario SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    contraseña VARCHAR(100) NOT NULL,
    direccion TEXT,
    telefono VARCHAR(20),
    rol_id INTEGER REFERENCES roles(id_rol),
    fecha_registro TIMESTAMP
);
```

#### Tabla `categorias`
```sql
CREATE TABLE categorias (
    id_categoria SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion TEXT
);
```

#### Tabla `marcas`
```sql
CREATE TABLE marcas (
    id_marca SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    pais_origen VARCHAR(50)
);
```

### Relaciones
- Un **Usuario** pertenece a un **Rol** (relación Many-to-One)
- Las **Categorías** y **Marcas** son entidades independientes para la futura gestión de productos

## 🔒 Validaciones

La API incluye validaciones robustas:

- **Email válido** y único para usuarios
- **Nombres requeridos** para todas las entidades
- **Contraseñas obligatorias** para usuarios
- **Referencias válidas** entre entidades (Usuario-Rol)
- **Longitud de campos** según especificaciones de base de datos

## 📝 Códigos de Estado HTTP

- `200 OK`: Operación exitosa
- `201 Created`: Recurso creado exitosamente
- `400 Bad Request`: Error de validación o datos inválidos
- `404 Not Found`: Recurso no encontrado
- `409 Conflict`: Conflicto (ej: email duplicado)
- `500 Internal Server Error`: Error interno del servidor

## 🤝 Contribuir

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📜 Licencia

Este proyecto está bajo la licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 👨‍💻 Autor

**Juan Sebastian Rios Altamirano** - Ingeniero de Sistemas - [@xsebastv](https://github.com/xsebastv)

## 📞 Soporte

Si tienes preguntas o necesitas soporte, puedes:
- Abrir un issue en el repositorio
- Contactar al desarrollador principal

---

⭐ ¡No olvides darle una estrella al proyecto si te resultó útil!