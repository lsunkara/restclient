Rest Client

1.To retrieve all photos either run

mvn clean install

java -jar target/restclient-1.0-SNAPSHOT.jar

or

mvn spring-boot:run

2. To retrieve photos by album id either run

mvn clean install

java -jar target/restclient-1.0-SNAPSHOT.jar 2

or

mvn spring-boot:run -Drun.arguments=2

