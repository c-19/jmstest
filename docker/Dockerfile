FROM jboss/wildfly:13.0.0.Final

RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#70365 --silent

EXPOSE 5555 9990 8080

ENV JAVA_OPTS "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5555 -Djava.net.preferIPv4Stack=true "

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

COPY files/jmstest-app.ear /opt/jboss/wildfly/standalone/deployments/