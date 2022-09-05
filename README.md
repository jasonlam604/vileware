# Vileware

## Overview
Vileware is an open source Gaming API Service with the intent to help assist provide a baseline foundational system you can enhance and continually build. Vileware provides a fully functional [Microservices](https://microservices.io/) boilerplate foundation for your project. All the code is contain in this one repo using a [Monorepo](https://monorepo.tools/) approach.

Key Product Domain areas:

- Player Services
- Title Services
- Studios Services
- see [Wiki, Product Services](https://github.com/jasonlam604/vileware/wiki/Product-Services) for the full list of services.

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

Full architecture documentation provided in the [Wiki, System Architecture](https://github.com/jasonlam604/vileware/wiki/System-Architecture) section



## Deployment

What is included in this project is the ability to deploy to [AWS](https://aws.amazon.com/) using [CDK](https://aws.amazon.com/cdk/), including the options to easily deploy to multiple environments:

- Production
- Staging
- QA 
- Adhoc emphermal environments

Full deployment information provided in the [Wiki, System Deployment](https://github.com/jasonlam604/vileware/wiki/System-Deployment) section

# Testing

## Unit Tests

## Smoke Test Services

### When using Docker

### When not using Docker
