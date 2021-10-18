package com.mercury.api;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.mercury"})
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableJpaRepositories
@EnableJpaAuditing
@EnableEncryptableProperties
public class MercuryBaseApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MercuryBaseApiApplication.class, args);
    }

}
