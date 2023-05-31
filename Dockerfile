FROM tomcat
COPY target/task-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/task-0.0.1-SNAPSHOT.war

CMD ["catalina.sh", "run"]