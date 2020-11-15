package com.prodev.dms.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.prodev.dms.repository")
@EntityScan(basePackages = "com.prodev.dms.domain")
@EnableTransactionManagement
@EnableAutoConfiguration
public class ApplicationConfig {

}
