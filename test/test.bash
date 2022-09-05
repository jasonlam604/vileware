#!/usr/bin/env bash
#
# Local Dev usage: HOST=localhost PORT=7000 ./test-em-all.bash
# Docker usage:  ./test-em-all.bash
#
# Note default to port 8080, see docker-compose.yml, expose edge service / engine service is set to port 8080
#
: ${HOST=localhost}
: ${PORT=8080}

## Test of Studio Endpoints
: ${STUDIO_ID=2}
: ${STUDIO_NAME_VILWARE=Vileware}
: ${STUDIO_ID_NOT_FOUND=99}

## Test Event Endpoints
: ${EVENT_ID=42}

## Test Stat Endpoints
: ${STAT_ID=666}

source functions.bash

set -e

echo "Start Tests:" `date`

echo "HOST=${HOST}"
echo "PORT=${PORT}"

if [[ $@ == *"start"* ]]
then
  echo "Restarting the test environment..."
  echo "$ docker-compose down --remove-orphans"
  docker-compose down --remove-orphans
  echo "$ docker-compose up -d"
  docker-compose up -d
fi

# This wait for services to go up
waitForService curl http://$HOST:$PORT/studios



echo "*************************************************************"
echo "* Studios tests                                             *"
echo "*************************************************************"
# Verify List of Studios, 3 should be returned
assertCurl 200 "curl http://$HOST:$PORT/studios -s"
assertEqual 3 $(echo $RESPONSE | jq ".data | length")

# Verify Find By Studio, using Studio Id 2, Vileware
assertCurl 200 "curl http://$HOST:$PORT/studios/$STUDIO_ID -s"
assertEqual "\"$STUDIO_NAME_VILWARE"\" $(echo $RESPONSE | jq ".data.name")

# Verify that a 422 (Not Found) error is returned for a non-existing studioId ($STUDIO_ID_NOT_FOUND)
assertCurl 404 "curl http://$HOST:$PORT/studios/$STUDIO_ID_NOT_FOUND -s"
assertEqual "No studio found for studioId: $STUDIO_ID_NOT_FOUND" "$(echo $RESPONSE | jq -r .message)"

echo "*************************************************************"
echo "* Event tests                                               *"
echo "*************************************************************"
# Verify Find By Event
assertCurl 200 "curl http://$HOST:$PORT/events/$EVENT_ID -s"
assertEqual $EVENT_ID $(echo $RESPONSE | jq ".data.id")

echo "*************************************************************"
echo "* Stat tests                                                *"
echo "*************************************************************"

# Verify Find By Stat
assertCurl 200 "curl http://$HOST:$PORT/stats/$STAT_ID -s"
assertEqual $STAT_ID $(echo $RESPONSE | jq ".data.id")

# Verify access to Swagger and OpenAPI URLs
echo "*************************************************************"
echo "* Swagger/OpenAPI tests                                     *"
echo "*************************************************************"
assertCurl 302 "curl -s  http://$HOST:$PORT/openapi/swagger-ui.html"
assertCurl 200 "curl -sL http://$HOST:$PORT/openapi/swagger-ui.html"
assertCurl 200 "curl -s  http://$HOST:$PORT/openapi/v3/api-docs"
assertEqual "3.0.1" "$(echo $RESPONSE | jq -r .openapi)"
assertCurl 200 "curl -s  http://$HOST:$PORT/openapi/v3/api-docs.yaml"

if [[ $@ == *"stop"* ]]
then
    echo "We are done, stopping the test environment..."
    echo "$ docker-compose down"
    docker-compose down
fi

echo "End, all tests OK:" `date`
