#!/usr/bin/env bash

docker stop rest-demo
docker rm rest-demo

docker run -d \
  --name rest-demo \
  -p 8082:8080 \
  -e DB_HOST="172.17.0.1" \
  -e DB_PORT="30014" \
  -e WILDFLY_USER="admin" \
  -e WILDFLY_PASSWORD="admin" \
  -e JNDI_NAME="java:jboss/jdbc/demo" \
  -e DATASOURCE_NAME="Demo" \
  -e DATABASE_NAME="Demo" \
  -e DATABASE_USER="sa" \
  -e DATABASE_PASSWORD="adesso@OpenShift" \
  adesso/customer-java-rest:1.0.2

docker logs -f rest-demo