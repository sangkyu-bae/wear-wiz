package com.wearwiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ViewsMain {
    public static void main(String[] args) {
        SpringApplication.run(ViewsMain.class, args);
    }
}
