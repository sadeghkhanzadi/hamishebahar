FROM eclipse-temurin:17-jdk-alpine
MAINTAINER SadeghKhanzadi@Gmail.com
ENV HOME=/usr/app
ENV VOLUME=/tmp/hamishebahar
RUN mkdir -p $HOME
RUN mkdir -p $VOLUME
VOLUME /tmp/hamishebahar
WORKDIR $HOME
COPY ./target/Posts-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "/Posts-0.0.1-SNAPSHOT.jar"]