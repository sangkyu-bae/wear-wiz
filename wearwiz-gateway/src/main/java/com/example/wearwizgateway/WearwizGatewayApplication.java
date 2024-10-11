package com.example.wearwizgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WearwizGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WearwizGatewayApplication.class, args);
    }

}
