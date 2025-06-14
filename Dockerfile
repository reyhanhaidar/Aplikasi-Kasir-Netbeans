FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .

# Install custom jars
RUN mvn install:install-file -Dfile=library/rs2xml.jar -DgroupId=net.proteanit -DartifactId=DbUtils -Dversion=1.0 -Dpackaging=jar
RUN mvn install:install-file -Dfile=library/mysql-connector-java-5.1.23-bin.jar -DgroupId=mysql -DartifactId=mysql-connector-java -Dversion=5.1.23 -Dpackaging=jar

RUN mvn clean package -DskipTests

# Runtime image with Xvfb and GUI dependencies
FROM eclipse-temurin:17-jre

# Install Xvfb and dependencies for Java GUI
RUN apt-get update && apt-get install -y \
    xvfb \
    libxtst6 \
    libxrender1 \
    libxi6 \
    && rm -rf /var/lib/apt/lists/*

ENV DISPLAY=:99

WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar

# Jalankan aplikasi menggunakan virtual framebuffer
CMD ["xvfb-run", "-a", "java", "-cp", "/app/app.jar", "kasir.Kasir"]
