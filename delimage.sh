#!/bin/bash

CONTAINER=$1
 
RUNNING=$(docker inspect $CONTAINER 2> /dev/null)

if [ $? -eq 1 ]; then
  echo "'$CONTAINER' does not exist."
else
  /usr/bin/docker image rm --force $CONTAINER
fi
