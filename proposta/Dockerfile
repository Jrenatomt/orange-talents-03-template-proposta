FROM openjdk:11
MAINTAINER Jose Renato
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV PROFILE=test
ENV analisa.proposta.api=http://localhost:9999/api/solicitacao
ENV cartao.api=http://localhost:8888/api/cartoes
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
