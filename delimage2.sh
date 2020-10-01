docker ps -a | awk '{ print $1,$2 }' | grep kkapelon/docker-maven-comparison | awk '{print $1 }' | xargs -I {} docker rm {} --force
