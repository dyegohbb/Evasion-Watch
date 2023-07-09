# Use a imagem base do Java
FROM openjdk:17-jdk-alpine

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo JAR da sua aplicação para o contêiner
COPY ./Evasion-Watch-0.0.1-SNAPSHOT.jar /app/app.jar

# Defina as variáveis de ambiente
ENV SERVER_ADDRESS=localhost \
    SERVER_PORT=8081 \
    DB_HOST=localhost \
    DB_PORT=3306 \
    DB_NAME=evasionwatch \
    DB_USERNAME=root \
    DB_PASSWORD=QweBHU* \
    DB_DDL=update \
    JWT_SECRET_KEY=45BE51A2E7C007C29A3DFEC435FFF726E55772E74533600AE7984F4D666852A7CF0B32398F8CB45E0B6C05F165DBE8BA8560CA2374D00017E958A47307AEF694 \
    JWT_EXPIRATION=3600000 \
    REFRESH_TOKEN_EXPIRATION=604800000 \
    MAX_FILE_SIZE=10MB \
    MAX_REQUEST_SIZE=100MB \
    KAFKA_BOOTSTRAP_SERVERS=localhost:9092

# Exponha a porta que sua aplicação está usando
EXPOSE ${SERVER_PORT}

# Comando para executar a aplicação
CMD ["java", "-jar", "app.jar"]
