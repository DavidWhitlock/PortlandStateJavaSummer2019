#!/bin/bash

source checkForJava.sh

if ! checkForJava; then
    echo "** Java is not configured correctly in your environment"
    exit 1
fi

if ! checkForMavenSettings; then
    echo "** Your Maven settings are not configured correctly in your environment"
    exit 1
fi

if [ "$#" -ne 1 ]; then
    echo "** Missing your student id"
    exit 1
fi

studentId=$1

./mvnw --batch-mode archetype:generate \
  -DinteractiveMode=false \
  -DarchetypeGroupId=edu.pdx.cs410J \
  -DarchetypeArtifactId=apptbook-web-archetype \
  -DarchetypeVersion=Summer2019 \
  -DgroupId=edu.pdx.cs410J.${studentId} \
  -DartifactId=apptbook-web \
  -Dpackage=edu.pdx.cs410J.${studentId} \
  -Dversion=Summer2019
