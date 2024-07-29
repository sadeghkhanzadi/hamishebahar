FROM eclipse-temurin:17-jdk-alpine
MAINTAINER khanzadisadegh@Gmail.com
#ENV HOME=/usr/app
##ENV VOLUME=/tmp/hamishebahar
#RUN mkdir -p $HOME
##RUN mkdir -p $VOLUME
##VOLUME /tmp/hamishebahar
#WORKDIR $HOME
#COPY ./target/hamishebahar-0.0.1.jar /app
#EXPOSE 8082
#CMD ["java", "-jar", "/hamishebahar-0.0.1.jar"]


WORKDIR /app
ENV HOME=/app
ENV VOLUME=/app/uploads
RUN mkdir -p $VOLUME
VOLUME $VOLUME
COPY ./hamishebahar-0.0.1.jar /app
EXPOSE 8080
CMD ["java", "-jar", "hamishebahar-0.0.1.jar"]