package com.mercury.mercurycloudconfig;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
@EnableEncryptableProperties
public class MercuryCloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(MercuryCloudConfigApplication.class, args);
    }

}
