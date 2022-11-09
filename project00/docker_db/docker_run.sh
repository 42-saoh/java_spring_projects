#!/bin/bash

docker build -t db .
docker run -d -p 5432:5432 -e POSTGRES_PASSWORD=password -v board:/var/lib/postgresql/data --name db db