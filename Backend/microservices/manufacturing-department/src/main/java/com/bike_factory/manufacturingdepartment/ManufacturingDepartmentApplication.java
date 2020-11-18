package com.bike_factory.manufacturingdepartment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDiscoveryClient
public class ManufacturingDepartmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManufacturingDepartmentApplication.class, args);
	}
	
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
