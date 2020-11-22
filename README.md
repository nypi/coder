# Reference project for the CROC Java school

## How to start it with docker

* docker ps -a

* docker run --name coder-postgres --publish 5432:5432 -e POSTGRES_PASSWORD=mysecretpassword -v /temp/mount:/var/lib/postgresql/data -d postgres

* docker network create coder-network

* docker network connect coder-network coder-postgres

* mvn spring-boot:build-image

* docker run --net=coder-network --publish 8080:8080 -e spring_profiles_active=prod coder:1.0-SNAPSHOT

* curl http://localhost:8080