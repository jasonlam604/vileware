/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.stat;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("io.vileware")
@OpenAPIDefinition(info =
@Info(
        title = "Vileware Stat Services API",
        version = "1.0.0",
        description = "Documentation Vileware Stat Services API v1.0.0"
)
)
public class StatServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatServiceApplication.class, args);
    }
}
