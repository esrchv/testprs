# test-starter

Proyecto backend base construido con Java 21, Maven y Spring Boot 3.5.x, empaquetado como WAR para despliegue en contenedor.

## Inicio rápido (2 minutos)

1. Validar compilación y pruebas:
   - `mvn clean verify`
2. Generar el WAR:
   - `mvn package`
3. Ejecutar localmente (servidor embebido):
   - `mvn spring-boot:run`
4. Verificar salud del servicio:
   - `GET /api/health`

## Stack tecnológico

- Java 21
- Maven
- Spring Boot 3.5.x
- Empaquetado WAR
- Spring Web, JDBC, Validation, Web Services
- OpenAPI (springdoc)

## Aspectos principales del proyecto

- Clase principal: `TestApplication` con scheduling habilitado y auto-configuración de datasource deshabilitada.
- `ServletInitializer` para despliegue en contenedor servlet externo.
- `DSConfig` con dos beans JNDI de `DataSource`:
  - `testoneDataSource`
  - `testtwoDataSource`
- Transaction managers para ambos data sources.
- Endpoint de salud: `GET /api/health`.

## Tareas básicas (referencia técnica)

### Compilar y ejecutar pruebas

- `mvn clean verify`

### Empaquetar WAR

- `mvn package`
- Omitir pruebas cuando sea necesario: `mvn package -DskipTests`

### Ejecutar localmente (servidor embebido)

- `mvn spring-boot:run`

### Documentación OpenAPI

- API docs: `/api-docs`
- Swagger UI: `/swagger-ui.html`

### Verificación de salud

- Endpoint: `GET /api/health`
- Ejemplo de respuesta:
  - `status`: `UP`
  - `application`: `test-starter`
  - `timestamp`: fecha-hora en formato ISO-8601

## Notas de configuración

El archivo `application.properties` utiliza solo placeholders (sin secretos reales):

- `testone.datasource.jndi-name`
- `testtwo.datasource.jndi-name`
- `logging.file.name`
- `spring.profiles.active`

Define valores por entorno mediante variables de entorno o configuración de despliegue.

## Flujo CI

El workflow de GitHub Actions está disponible en:

- `.github/workflows/maven-build.yml`

Ejecuta verify/package en push y pull request hacia la rama `j21api`, y publica artefactos WAR.
