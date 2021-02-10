FROM maven:3-jdk-11 as maven_builder

WORKDIR /app
ADD web-development .
RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "clean", "package"]


FROM tomcat:9-jdk11

COPY --from=maven_builder /app/target/python.war $CATALINA_HOME/webapps/
# ADD phrase-mining /app
