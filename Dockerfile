# Build
FROM openjdk:21-oracle AS builder

WORKDIR /app

COPY . .

# Ejecuta mvn clean package sin correr los tests
RUN ./mvnw clean package -DskipTests


FROM openjdk:21-oracle

# Crea directorio de la app
WORKDIR /app

# Copia el JAR de tu microservicio (asegúrate que el nombre coincida)
COPY --from=builder /app/target/*.jar app.jar

COPY wallet /app/wallet

# Expone el puerto (si tu microservicio usa el 8080 por ejemplo)
EXPOSE 8080


# Comando para correr la app
ENTRYPOINT ["java", "-jar", "app.jar"]