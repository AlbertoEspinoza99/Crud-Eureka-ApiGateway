package com.centroinformacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Sw2Java17EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sw2Java17EurekaServerApplication.class, args);
	}

}
