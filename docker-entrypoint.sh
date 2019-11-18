#!/bin/sh

echo 'Sleeping 30s...'
sleep 30

echo 'Starting...'
java -jar -Dspring.profiles.active=prod /catalog.jar