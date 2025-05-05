# Build
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

COPY . .

# Ejecuta mvn clean package sin correr los tests
RUN ./mvnw clean package -DskipTests


FROM openjdk:21-ea-24-oracle

# Crea directorio de la app
WORKDIR /app

# Copia el JAR de tu microservicio (asegúrate que el nombre coincida)
COPY --from=builder /app/target/*.jar app.jar

COPY wallet /app/wallet

# Variables entorno
ENV ORACLE_PATH_DATASOURCE="jdbc:oracle:thin:@dbaplicada_high?TNS_ADMIN=/app/wallet"
ENV DB_MASCOTA_USER=MASCOTAS
ENV DB_MASCOTA_PASS=MasMsSum.US3r_BD

ENV MS_USER=admin
ENV MS_PASS=admin123

# Expone el puerto (si tu microservicio usa el 8080 por ejemplo)
EXPOSE 8080


# Comando para correr la app
ENTRYPOINT ["java", "-jar", "app.jar"]