FROM eclipse-temurin:17-jdk-alpine
ADD target/*.jar /app/pci-vault.jar
WORKDIR /app
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "pci-vault.jar"]