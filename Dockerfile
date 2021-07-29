#Este archivo construye un contenedor desde una imagen de gradle en el Fork de la rama principarl del proyecto "helo-gradle"

#usamos una imagen de gradle que monta un open jdk11:
#BUILD STAGE
FROM openjdk:11 AS base
#crear un directorio de trabajo:
WORKDIR /opt/hello-srping
#Copiar en el workdir lo de gradle
COPY ./ ./
#COPY build/libs/demo-0.0.1-SNAPSHOT.jar ./
#Correr el SpringBoot
#CMD ./gradlew bootRun
#RUN ./gradlew assemble --> generar el .jar
#RUN ./gradlew assemble
RUN ./gradlew assemble

# RUNTIME STAGE
FROM amazoncorretto:11
WORKDIR /opt/hello-srping
COPY --from=base /opt/hello-srping/build/libs/hello-srping-0.0.1-SNAPSHOT.jar ./

CMD java -jar hello-srping-0.0.1-SNAPSHOT.jar
# java -jar build/libs/demo-0.0.1-SNAPSHOT.jar | Para hacerlo desde el ejecutable pre creado.