# Quarkus
1 - Run application :<br/>
$ mvn quarkus:dev <br/><br/>
2 - Generate image container <br/>
$ mvn clean package -DskipTests -Dquarkus.container-image.build=true<br/><br/>
3 - Run container <br/>
$ docker run -i --rm -p 8080:8081 nasne/people-service:1.0.0-SNAPSHOT<br/><br/>
4 - Test rest API <br/>
$ curl http://localhost:8080/hello