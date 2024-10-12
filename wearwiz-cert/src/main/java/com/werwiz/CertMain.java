package com.werwiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CertMain {
    public static void main(String[] args) {
        SpringApplication.run(CertMain.class, args);
    }
}
