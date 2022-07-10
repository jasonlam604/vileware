# Vileware

## Overview
Vileware is an open source Gaming API Service with the intent to help assist provide a baseline foundational system you can enhance and continually build. Vileware provides a fully functional [Microservices](https://microservices.io/) boilerplate foundation for your project. All the code is contain in this one repo using a [Monorepo](https://monorepo.tools/) approach.

Key Product Domain areas:

- Player Services
- Title Services
- Studios Services
- see [Wiki, Product Services](https://github.com/jasonlam604/vileware/wiki/Product-Services) for the full list of services.

## Tech Stack Overview
- [Docker](https://www.docker.com/)
- Bash
- [Java 17](https://www.java.com/en/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Lombok](https://projectlombok.org/)
- [Gradle](https://gradle.org/)
- IDE of your choice will make your life much easier, I'm using [Intellij Community Edition](https://www.jetbrains.com/idea/download/)
- AWS Services & Tools
   - [AWS Cloud Development Kit / CDK] (https://aws.amazon.com/cdk/) using [Maven](https://maven.apache.org/) and [Java](https://www.java.com/en/)
   - [Amazon Elastic Container Registry / ECR](https://aws.amazon.com/ecr/)
   - [Amaonz Simple Notification Service / SNS](https://aws.amazon.com/sns/)
   - [Amazon Simple Queue Service / SQS](https://aws.amazon.com/sqs/)
   - [Amazon Virtual Private Cloud / VPC](https://aws.amazon.com/vpc/)
   - [Amazon X-RAY](https://aws.amazon.com/xray/)
   - [Amazon RDS - Postgres](https://aws.amazon.com/rds/)

## Source Code Layout Overview
The Vileware respository at the root contains the source code under **/application** which is made up of microservices utilizing a [Gradle Multi Project Layout](https://docs.gradle.org/current/userguide/multi_project_builds.html); all the services are contained in this one GitHub respostiory that utilizes a [Monorepo](https://monorepo.tools/) approach.  Then under **/cdk** are the deployment scripts using Java with [CDK](https://aws.amazon.com/cdk/).

```
/root
  |
  |-/application
  |
  |
  |----
  
  |
  |-/cdk
```

## Architecture

Full architecture documentation provided in the [Wiki, System Architecture](https://github.com/jasonlam604/vileware/wiki/System-Architecture) section



## Deployment

What is included in this project is the ability to deploy to [AWS](https://aws.amazon.com/) using [CDK](https://aws.amazon.com/cdk/), including the options to easily deploy to multiple environments:

- Production
- Staging
- QA 
- Adhoc emphermal environments

Full deployment information provided in the [Wiki, System Deployment](https://github.com/jasonlam604/vileware/wiki/System-Deployment) section

