# issue-classloader
Reproduce issue related to https://github.com/spring-projects/spring-boot/issues/19098

`mvn spring-boot:run`
Works fine - see that a null stream is returned

`java -jar target/issue-class-loader-1.0-SNAPSHOT.jar`
fails with the exception.
