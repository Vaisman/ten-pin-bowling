FROM openjdk:8-jre
ADD ten-pin-bowling.jar ten-pin-bowling.jar
ENTRYPOINT java -jar ten-pin-bowling.jar