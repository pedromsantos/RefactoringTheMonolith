package com.monolitospizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("integration-context.xml")
@EnableDiscoveryClient
public class RestaurantStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantStoreApplication.class, args);
	}
}
