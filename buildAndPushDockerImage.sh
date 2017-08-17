#!/usr/bin/env bash

mvn clean install

docker build -t adesso/customer-java-rest:1.0.2 .
docker push adesso/customer-java-rest:1.0.2