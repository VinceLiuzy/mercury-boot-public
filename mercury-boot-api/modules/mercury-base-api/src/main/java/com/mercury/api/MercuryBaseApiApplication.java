package com.mercury.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.mercury"})
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.mercury")
@EntityScan(basePackages = "com.mercury")
@EnableJpaAuditing
public class MercuryBaseApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MercuryBaseApiApplication.class, args);
    }

}
