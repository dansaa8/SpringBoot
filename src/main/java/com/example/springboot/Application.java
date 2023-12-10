package com.example.springboot;

import org.geolatte.geom.G2D;
import org.geolatte.geom.crs.CoordinateReferenceSystem;
import org.geolatte.geom.json.GeolatteGeomModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

// Innehåller @Configuration, @EnableAutoConfiguration, @ComponentScan
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean // Behövs för att serialisera geolatte obj. till JSON
	GeolatteGeomModule geolatteGeomModule() {
		CoordinateReferenceSystem<G2D> crs = WGS84;
		return new GeolatteGeomModule();
	}

	@Bean
	public RestClient createClient() {
		return RestClient.builder()
				.baseUrl("https://localhost:8080/")
				.build();
	}
}
