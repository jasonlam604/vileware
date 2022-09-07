# Vileware

## Overview
Vileware is an open source Gaming API Service with the intent to help assist provide a baseline foundational system you can enhance and continually build. 

Vileware is made up a number of backend RESTful API services with the intent to be being utilized for video games. It does NOT provide video game engine services or any game specific behaviours but it rather provides common cross cutting concerns for one or more games. The key value of the services are:

- Re-usability of services across more than one game franchise and/or game title.
- KYP, Know Your Player, by having the capability to manage your each player across multiple titles allows you to more easily gain insights to your players behaviours and ultimately provide a improve player experience
- Each service is individualized, you select the services of interest and are able to scale each service when required

Vileware provides a fully functional [Microservices](https://microservices.io/) boilerplate foundation for your project. All the code is contain in this one repo using a [Monorepo](https://monorepo.tools/) approach.

The Monorepo approach allows one, literally one software engineer to pragmatically build a system from ground up but yet have flexibility to scale
out where needed based on services with super ease of viewing all the code in one spot.  There was an explicit decision to have a common library
that would allow each service to share commonalities such as DTOs, HTTP utilities...etc. Arguably this could be viewed as an anti-pattern namely *distrubted monolith*, that said what is being gained here is the support of DRY, Do Not Repeat Yourself.

> **_NOTE:_**  Project currently uses Gradle for Monorepo admittly this is more Java centric, potentially revisit the tooling, such as [Bazel](https://bazel.build/)

Once the system orangically grows and each system naturally supports the organic growth of teams aka PODs, this allows each POD to agnostically run at their own speeds and grow accordingly whether it's technically or from a POD team member count perspective

> **_NOTE:_**  Initially from the perspective of managability ideally sticking to a common tech stack makes everything easier in this case the Java ecosystem, that said there is no reason why as the team grows and where it make sense different tech stacks can be introduced, again then to keep with a Monorepo approach Gradle may not be the best tool to use, see [Bazel](https://bazel.build/)

Here, is what the system looks like with 1 common library and 4 services where engine service is the edge service (Microservice Aggregator Pattern). As you can see a software engineer easily can view the entire project and yet still feasiably manage a microservice environment locally using Docker or simply by starting each service. Take note this project is prepped (yet to be completed) to be deployed into a Cloud Native environment, intent to be deployable in AWS using CDK (Java & Maven) along with the use of GitHub Actions.

![image](https://github.com/jasonlam604/vileware/blob/main/doc/images/vileware-ide-screenshot.png)

## Objectives

- [x] Release 0.1.0 - Foundational work: Gradle Multi Project, Mono Repo, Docker, OpenAPI for each service, Unit Test, Smoke Integration Test
- [ ] Release 0.2.0 - Add in Persistence, Postgres or MySQL
- [ ] Release 0.3.0 - Add in Async Logging via Messaging using RabbitMQ / Amazon MQ
- [ ] Release 0.4.0 - Add in Caching via Redis
- [ ] Release x.x.x - To Be Determined


## Tech Stack Overview
- [x] [Docker](https://www.docker.com/) and [Docker Compose](https://docs.docker.com/get-started/08_using_compose/)
- [x] [Bash](https://www.gnu.org/software/bash/), used for smoke testing integration
- [x] [Java 17](https://www.java.com/en/)
- [x] [Spring Boot](https://spring.io/projects/spring-boot)
- [x] [Lombok](https://projectlombok.org/)
- [x] [Gradle](https://gradle.org/)
- [x] [Intellij Community Edition](https://www.jetbrains.com/idea/download/) is the IDE shown in the screenshots
- [x] JUnit used for unit testing isolated services
- [x] Mockito used for unit testing the behavior of a specific service under test without worrying about its dependencies on other services
- [ ] AWS Services & Tools
   - [ ] [AWS Cloud Development Kit / CDK] (https://aws.amazon.com/cdk/) using [Maven](https://maven.apache.org/) and [Java](https://www.java.com/en/)
   - [ ] [Amazon Elastic Container Registry / ECR](https://aws.amazon.com/ecr/)
   - [ ] [Amazon Simple Notification Service / SNS](https://aws.amazon.com/sns/)
   - [ ] [Amazon Simple Queue Service / SQS](https://aws.amazon.com/sqs/)
   - [ ] [Amazon Virtual Private Cloud / VPC](https://aws.amazon.com/vpc/)
   - [ ] [Amazon X-RAY](https://aws.amazon.com/xray/)
   - [ ] [Amazon RDS - Postgres](https://aws.amazon.com/rds/)

## Source Code Layout Overview
The Vileware respository at the root contains the source code under **/application** which is made up of microservices utilizing a [Gradle Multi Project Layout](https://docs.gradle.org/current/userguide/multi_project_builds.html); all the services are contained in this one GitHub respostiory that utilizes a [Monorepo](https://monorepo.tools/) approach.  Then under **/cdk** are the deployment scripts using Java with [CDK](https://aws.amazon.com/cdk/).

```
/root
  |
  |-/application
  |     |
  |     |- docker.compose.yml
  |     |- settings.gradle
  |     |
  |     |-/common
  |     |    |
  |     |    |-/src/main/java
  |     |    |
  |     |    |- build.gradle  
  |     |
  |     |-/services
  |          |
  |          |-/engine-service
  |          |     |
  |          |     |-/src
  |          |     |   |-/main
  |          |     |   |   |-/resources/application.yml
  |          |     |   |
  |          |     |   |-/test
  |          |     |
  |          |     |- build.gradle
  |          |
  |          |-/core-service
  |          |     |
  |          |     |-/src
  |          |     |   |-/main
  |          |     |   |   |-/resources/application.yml
  |          |     |   |  
  |          |     |   |-/test
  |          |     |
  |          |     |- build.gradle
  |          |
  |          |-/event-service
  |          |     |
  |          |     |-/src
  |          |     |   |-/main
  |          |     |   |   |-/resources/application.yml
  |          |     |   |  
  |          |     |   |-/test
  |          |     |
  |          |     |- build.gradle
  |          |
  |          |-/stat-service
  |                |
  |                |-/src
  |                |   |-/main
  |                |   |   |-/resources/application.yml
  |                |   |  
  |                |   |-/test
  |                |
  |                |- build.gradle
  |
  |-/cdk
  |   |
  |   |--[Coming soon CDK scripts using Java and Maven]
  |
  |-/test
      |
      |-test.bash
      |
      |-functions.bash
      
```
## Quick Start Guide

Prerequisites
- Java 17
- Docker with Docker Compose
- Gradle

Clone Projects
```
git clone git@github.com:jasonlam604/vileware.git
```

Navigate to application folder
```
cd ./vileware/application
```

Clean Project
```
gradle clean
```

Build Project, unit tests will execute as well
```
gradle build
```

Staying in the same folder ~/vileware/application Start services using docker

**Optional Prune System** warning this will remove unused / dangling (stopped containers, networks, images...)
```
docker-compose system prune
```

```
docker-compose up
```


You should see something similar to this
```
Creating application_event_1  ... done
Creating application_core_1   ... done
Creating application_engine_1 ... done
Attaching to application_stat_1, application_event_1, application_engine_1, application_core_1
stat_1    | 
stat_1    |   .   ____          _            __ _ _
stat_1    |  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
stat_1    | ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
stat_1    |  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
stat_1    |   '  |____| .__|_| |_|_| |_\__, | / / / /
stat_1    |  =========|_|==============|___/=/_/_/_/
stat_1    |  :: Spring Boot ::                (v2.7.1)
stat_1    | 
engine_1  | 
engine_1  |   .   ____          _            __ _ _
engine_1  |  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
engine_1  | ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
engine_1  |  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
engine_1  |   '  |____| .__|_| |_|_| |_\__, | / / / /
engine_1  |  =========|_|==============|___/=/_/_/_/
engine_1  |  :: Spring Boot ::                (v2.7.1)
engine_1  | 
event_1   | 
event_1   |   .   ____          _            __ _ _
event_1   |  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
event_1   | ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
event_1   |  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
event_1   |   '  |____| .__|_| |_|_| |_\__, | / / / /
event_1   |  =========|_|==============|___/=/_/_/_/
event_1   |  :: Spring Boot ::                (v2.7.1)
event_1   | 
core_1    | 
core_1    |   .   ____          _            __ _ _
core_1    |  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
core_1    | ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
core_1    |  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
core_1    |   '  |____| .__|_| |_|_| |_\__, | / / / /
core_1    |  =========|_|==============|___/=/_/_/_/
core_1    |  :: Spring Boot ::                (v2.7.1)
core_1    | 
engine_1  | 2022-09-05 19:08:15.787  INFO 1 --- [           main] i.v.engine.EngineServiceApplication      : Starting EngineServiceApplication using Java 17.0.4.1 on 120d63a014b4 with PID 1 (/application/BOOT-INF/classes started by root in /application)
engine_1  | 2022-09-05 19:08:15.799 DEBUG 1 --- [           main] i.v.engine.EngineServiceApplication      : Running with Spring Boot v2.7.1, Spring v5.3.21
engine_1  | 2022-09-05 19:08:15.806  INFO 1 --- [           main] i.v.engine.EngineServiceApplication      : The following 1 profile is active: "docker"
stat_1    | 2022-09-05 19:08:15.903  INFO 1 --- [           main] io.vileware.stat.StatServiceApplication  : Starting StatServiceApplication using Java 17.0.4.1 on 20e2b85278c9 with PID 1 (/application/BOOT-INF/classes started by root in /application)
stat_1    | 2022-09-05 19:08:15.911 DEBUG 1 --- [           main] io.vileware.stat.StatServiceApplication  : Running with Spring Boot v2.7.1, Spring v5.3.21
stat_1    | 2022-09-05 19:08:15.914  INFO 1 --- [           main] io.vileware.stat.StatServiceApplication  : The following 1 profile is active: "docker"
core_1    | 2022-09-05 19:08:15.922  INFO 1 --- [           main] io.vileware.core.CoreServiceApplication  : Starting CoreServiceApplication using Java 17.0.4.1 on 46d1479f55d7 with PID 1 (/application/BOOT-INF/classes started by root in /application)
core_1    | 2022-09-05 19:08:15.937 DEBUG 1 --- [           main] io.vileware.core.CoreServiceApplication  : Running with Spring Boot v2.7.1, Spring v5.3.21
core_1    | 2022-09-05 19:08:15.939  INFO 1 --- [           main] io.vileware.core.CoreServiceApplication  : The following 1 profile is active: "docker"
event_1   | 2022-09-05 19:08:15.960  INFO 1 --- [           main] i.v.event.EventServiceApplication        : Starting EventServiceApplication using Java 17.0.4.1 on b0e8b3ecd002 with PID 1 (/application/BOOT-INF/classes started by root in /application)
event_1   | 2022-09-05 19:08:15.987 DEBUG 1 --- [           main] i.v.event.EventServiceApplication        : Running with Spring Boot v2.7.1, Spring v5.3.21
event_1   | 2022-09-05 19:08:15.988  INFO 1 --- [           main] i.v.event.EventServiceApplication        : The following 1 profile is active: "docker"
engine_1  | 2022-09-05 19:08:23.193  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
engine_1  | 2022-09-05 19:08:23.233  INFO 1 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
engine_1  | 2022-09-05 19:08:23.235  INFO 1 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.64]
stat_1    | 2022-09-05 19:08:23.407  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
.
..
...
.... etc
```

At this point for a quick verification see [Smoke Test](#smoke-test-services)

## Architecture

Full architecture documentation provided in the Vileware [Wiki](https://github.com/jasonlam604/vileware/wiki), in progress

- [ ] [Business Architecture & Product Services](https://github.com/jasonlam604/vileware.io/wiki/Business-Architecture--&-Product-Services)
- [ ] [System Application Architecture](https://github.com/jasonlam604/vileware.io/wiki/System-Application-Architecture)
- [ ] [Infrastructure Architecture](https://github.com/jasonlam604/vileware.io/wiki/Infrastructure-Architecture)


## Deployment

What is included in this project is the ability to deploy to [AWS](https://aws.amazon.com/) using [CDK](https://aws.amazon.com/cdk/), including the options to easily deploy to multiple environments:

- Production
- Staging
- QA 
- Adhoc emphermal environments

Full deployment information provided in the [Infrastructure Architecture](https://github.com/jasonlam604/vileware.io/wiki/Infrastructure-Architecture) section, in progress.

# Testing

## Unit Tests

Trivial unit tests available with the intent to provide boilerplate pattern to follow under each of the services

```
  |          |-/[N-service]
  |          |     |
  |          |     |-/src
  |          |     |   |-/main
  |          |     |   |   |-/resources/application.yml
  |          |     |   |
  |          |     |   |-/test
```
Take note the engine-service, the edge service that models after the Microservice Aggregator Pattern, has dependicies on other services to avoid a coupling and to only test behaviours of that service Mockito is used for Mock Objects.

## Smoke Test Services

Assuming you the docker containers running or are running the services individual on the 7xxx ports.

### When using Docker

in ~/vileware/test, run the following:

```
./test.bash
```

A successful output:

```
HOST=localhost
PORT=8080
Wait for: curl http://localhost:8080/studios... DONE, continues...
*************************************************************
* Studios tests                                             *
*************************************************************
Test OK (HTTP Code: 200)
Test OK (actual value: 3)
Test OK (HTTP Code: 200)
Test OK (actual value: "Vileware")
Test OK (HTTP Code: 404, {"status":"failure","message":"No studio found for studioId: 99","code":404,"data":null})
Test OK (actual value: No studio found for studioId: 99)
*************************************************************
* Event tests                                               *
*************************************************************
Test OK (HTTP Code: 200)
Test OK (actual value: 42)
*************************************************************
* Stat tests                                                *
*************************************************************
Test OK (HTTP Code: 200)
Test OK (actual value: 666)
*************************************************************
* Swagger/OpenAPI tests                                     *
*************************************************************
Test OK (HTTP Code: 302, )
Test OK (HTTP Code: 200)
Test OK (HTTP Code: 200)
Test OK (actual value: 3.0.1)
Test OK (HTTP Code: 200)
```

### When not using Docker

This is the same as running against Docker container except the services are not hidden behind the edge service (engine service) and 
are expose individually on seperate ports.  

```
 HOST=localhost PORT=7000 ./test.bash
```

Currently, the ports are set to 7xxx (7000s) see */engine-service/src/main/resources/application.yml*

```
...
...
app:
  core-service:
    host: localhost
    port: 7001
  event-service:
    host: localhost
    port: 7002
  stat-service:
    host: localhost
    port: 7003
...
...
```

Spring Profile activated with Docker sets everything to port 8080
```
spring.config.activate.on-profile: docker

server.port: 8080

app:
  core-service:
    host: core
    port: 8080
  event-service:
    host: event
    port: 8080
  stat-service:
    host: stat
    port: 8080
```

# API Documentation / Open API

Each of the services are prepared serve OpenAPI documentation, currently the Engine service is viewable both running via Docker or as individual service:

- Individual Service: http://localhost:7000/openapi/swagger-ui/index.html 
- Docker: http://localhost:8080/openapi/swagger-ui/index.html

Example of what it looks like:

![image](https://github.com/jasonlam604/vileware/blob/main/doc/images/vileware-open-api-engine-service-screenshot.png)

As for the internal service that intended to be served behind the Aggregator service one can currently only view them when the services are ran individually outside of Docker:

- Engine Service http://localhost:7000/openapi/swagger-ui/index.html 
- Core Service http://localhost:7001/openapi/swagger-ui/index.html 
- Event Service http://localhost:7002/openapi/swagger-ui/index.html 
- Stat Service http://localhost:7003/openapi/swagger-ui/index.html 

For them to be serviced in all in one location and to utilized the drop down, you'll need to configure service discovery such as Eureka and Spring Cloud. This may be included in future releases.



