package com.monolitospizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("integration-context.xml")
public class RestaurantStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantStoreApplication.class, args);
	}
}
