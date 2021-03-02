#!/bin/sh
java -jar -Dspring.profiles.active=core,relationaldb /opt/app/app.jar 