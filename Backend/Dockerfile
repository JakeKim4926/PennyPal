FROM openjdk:17

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

ENV SPRING_PROFILES_ACTIVE=prod \
    MYSQL_DATASOURCE_URL="jdbc:mysql://3.35.170.191:3307/penny_pal?serverTimezone=Asia/Seoul&characterEncoding=UTF-8" \
    MYSQL_DATASOURCE_USERNAME="root" \
    MYSQL_DATASOURCE_PASSWORD="108"

ENTRYPOINT ["java","-jar","/app.jar"]
