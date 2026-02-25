# Usa imagem oficial Java 17
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copia o jar gerado
COPY target/api-0.0.1-SNAPSHOT.jar app.jar

# Porta dinâmica do Render
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=prod -Dserver.port=$PORT -jar app.jar"]