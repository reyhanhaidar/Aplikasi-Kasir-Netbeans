FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .

RUN mvn install:install-file -Dfile=library/rs2xml.jar -DgroupId=net.proteanit -DartifactId=DbUtils -Dversion=1.0 -Dpackaging=jar
RUN mvn install:install-file -Dfile=library/mysql-connector-java-5.1.23-bin.jar -DgroupId=mysql -DartifactId=mysql-connector-java -Dversion=5.1.23 -Dpackaging=jar

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app

# install X virtual framebuffer (Xvfb)
RUN apt-get update && apt-get install -y xvfb && rm -rf /var/lib/apt/lists/*

COPY --from=build /app/target/*.jar /app/app.jar

# run headless using xvfb
CMD ["xvfb-run", "-a", "java", "-cp", "/app/app.jar", "kasir.Kasir"]
