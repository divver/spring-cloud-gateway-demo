package com.example.service1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Service2Application {

	@Bean
	public WebClient webClient(){
		return WebClient.create();
	}

	public static void main(String[] args) {
		SpringApplication.run(Service2Application.class, args);
	}
}
