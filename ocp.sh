#!/bin/bash

./mvnw clean package -DskipTests -Dquarkus.kubernetes.deploy=true
