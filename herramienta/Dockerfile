# Usamos una imagen base oficial de Java
FROM eclipse-temurin:17-jdk-jammy

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el archivo de configuración Maven Wrapper y el pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Damos permisos de ejecución al mvnw
RUN chmod +x mvnw

# Instalamos las dependencias y construimos el proyecto sin tests para acelerar
RUN ./mvnw dependency:go-offline

# Copiamos el código fuente
COPY src src

# Empaquetamos la aplicación (sin tests para acelerar)
RUN ./mvnw package -DskipTests

# Exponemos el puerto en el que correrá la app
EXPOSE 8080

# Comando para ejecutar la app empaquetada
CMD ["java", "-jar", "target/herramienta-0.0.1-SNAPSHOT.jar"]