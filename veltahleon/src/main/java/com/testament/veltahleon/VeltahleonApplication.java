package com.testament.veltahleon;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VeltahleonApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeltahleonApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner run() {
//		return args -> {
//
//        };
//	}

//	@Bean
//	public ModelMapper getModelMapper() {
//		return new ModelMapper();
//	}

	//CORS Error Resolution
//	@Bean
//	public CorsConfigurationSource corsFilter() {
//		UrlBasedCorsConfigurationSource cors = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration corsConfig = new CorsConfiguration();
//		corsConfig.setAllowCredentials(true);
//		corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//		corsConfig.setAllowedHeaders(Arrays.asList(
//				"Origin",
//				"Access-Control-Allow-Origin",
//				"Content-Type",
//				"Accept",
//				"Jwt-Token",
//				"Authorization",
//				"Origin, Accept",
//				"X-Requested-With",
//				"Access-Control-Request-Method",
//				"Access-Control-Request-Headers"
//		));
//		corsConfig.setExposedHeaders(Arrays.asList(
//				"Origin",
//				"Content-Type",
//				"Accept",
//				"Jwt-Token",
//				"Authorization",
//				"Access-Control-Allow-Origin",
//				"Access-Control-Allow-Credentials",
//				"Filename"
//		));
//		corsConfig.setAllowedMethods(Arrays.asList(
//				"GET",
//				"POST",
//				"PUT",
//				"PATCH",
//				"DELETE",
//				"OPTIONS"
//		));
//		cors.registerCorsConfiguration("/**", corsConfig);
//		return cors;
//	}
}