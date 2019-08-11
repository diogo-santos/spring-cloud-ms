#!/bin/bash


echo "Building projects..."
mvn clean package

echo "Starting config server ..."
gnome-terminal -- java -jar config-server/target/config-server-0.0.1-SNAPSHOT.jar

echo "Starting eureka server ..."
gnome-terminal -- java -jar eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar

while ! nc -z localhost 8888 ; do
    echo "Waiting for upcoming Config Server"
    sleep 10
done

echo "Starting services..."
gnome-terminal -- java -jar person-services/target/person-services-0.0.1-SNAPSHOT.jar
gnome-terminal -- java -jar person-contact-services/target/person-contact-services-0.0.1-SNAPSHOT.jar
gnome-terminal -- java -jar person-business-services/target/person-business-services-0.0.1-SNAPSHOT.jar
gnome-terminal -- java -jar person-webapp/target/person-webapp-0.0.1-SNAPSHOT.jar

echo "Check Eureka server at localhost:8761 in order to see all registered services"

exit 0