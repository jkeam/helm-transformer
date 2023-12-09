#!/bin/bash

podman run -ti --rm -p 2222:2222 \
-e PASSWORD_ACCESS=true \
-e USER_NAME=ftpuser \
-e USER_PASSWORD=ftppassword \
-e DOCKER_MODS=linuxserver/mods:openssh-server-openssh-client \
linuxserver/openssh-server

echo 'started ftp!'
