#!/bin/bash

echo "Starting config server ..."
gnome-terminal -- java -jar config-server/target/config-server-0.0.1-SNAPSHOT.jar

echo "Starting eureka server ..."
gnome-terminal -- java -jar eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar

while ! nc -z localhost 8888 ; do
    echo "Waiting for upcoming config server..."
    sleep 10
done

echo "Starting services..."
gnome-terminal -- java -jar gateway-server/target/gateway-server-0.0.1-SNAPSHOT.jar
gnome-terminal -- java -jar customer-services/target/customer-services-0.0.1-SNAPSHOT.jar
gnome-terminal -- java -jar customer-contact-services/target/customer-contact-services-0.0.1-SNAPSHOT.jar
gnome-terminal -- java -jar customer-business-services/target/customer-business-services-0.0.1-SNAPSHOT.jar
gnome-terminal -- java -jar customer-webapp/target/customer-webapp-0.0.1-SNAPSHOT.jar

echo "All services have started"

exit 0