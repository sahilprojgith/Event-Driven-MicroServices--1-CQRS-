package com.sahil.ProductService;

import com.sahil.ProductService.command.api.exception.ProductsServiceEventsErrorhandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	public void configure(EventProcessingConfigurer configurer){
		configurer.registerListenerInvocationErrorHandler(
				"product",
				configuration -> new ProductsServiceEventsErrorhandler()
		);
	}

}
