package com.bike_factory.circuitbreakerdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
@EnableEurekaClient
@EnableHystrixDashboard
public class CircuitBreakerDashboardApplication {

	//Gateway-Service Stream for Hystrix-Dashboard: http://localhost:8092/actuator/hystrix.stream
	public static void main(String[] args) {
		SpringApplication.run(CircuitBreakerDashboardApplication.class, args);
	}

}
