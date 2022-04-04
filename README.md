# Birdnoise server
This repository is part of the birdnoise project, in which ornithologists play sounds to birds in forests to make science.
The sounds are played on an esp8266 based device, more info: https://github.com/klucsik/noise-generator-for-bird-research
The logs are collected with an another esp8266 device, more info: https://github.com/Levente007/bird-noise-generation-logcollector

## Running locally:
The server is made up of two parts: a Frontend and a Backend, you need to start the two service separateliy.
You need **env variables**:
### Backend: 
 * **WEBUPDATE_URL:**
A simple arduino http update server which returns the url of the  most recent device version.
 Based on   https://github.com/klucsik/arduino-webupdate-service .
 A running deployment: http://arduino-webupdate.klucsik.fun
### Frontend: 
* **BACKEND_URL**
The url for the Backend part, with the /api basepath. Usually: localhost:8080/api
* **SERVER_PORT**=8081

## Migrating between versions
### 1.0.8 -> 1.0.9
The the messageCode column device_log table changed datatype. Apply the following command to the database:

`ALTER TABLE device_log ALTER COLUMN message_code  TYPE integer USING (message_code::integer);`