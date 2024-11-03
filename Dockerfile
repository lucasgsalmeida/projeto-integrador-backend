# Etapa 1: Construir a aplicação com Maven
FROM maven:3.8.6-eclipse-temurin-17 as builder

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo pom.xml e baixar as dependências
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar o restante do código-fonte para o container
COPY . .

# Construir o JAR da aplicação
RUN mvn clean package -DskipTests

# Etapa 2: Executar a aplicação em uma imagem mais leve
FROM eclipse-temurin:17-jdk-jammy

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o JAR da aplicação da etapa de build para esta imagem final
COPY --from=builder /app/target/*.jar app.jar

# Expor a porta do Spring Boot
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
