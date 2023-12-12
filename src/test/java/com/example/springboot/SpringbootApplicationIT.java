//package com.example.springboot;
//
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.web.reactive.server.WebTestClient;
//import org.testcontainers.containers.MySQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.hasSize;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Testcontainers
//@TestPropertySource(properties = {
//        "spring.test.database.replace=none",
//        "spring.sql.init.mode=always"})
//public class SpringbootApplicationIT {
//
//
//    @LocalServerPort // Får portnumret här. Kan dra igång många tester parallelt.
//    private int port;
//
//    @Container
//    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.2.0");
//
//    WebTestClient client;
//
//    @DynamicPropertySource
//    static void configureProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", mysql::getJdbcUrl);
//        registry.add("spring.datasource.username", mysql::getUsername);
//        registry.add("spring.datasource.password", mysql::getPassword);
//    }
//
//    @BeforeEach
//    void setUp() {
//        RestAssured.baseURI = "http://localhost:" + port;
//        client = WebTestClient.bindToServer().baseUrl("http://localhost:" + port ).build();
//    }
//
//    @Test
//    void shouldGetAllCategories() {
//        given()
//                .contentType(ContentType.JSON)
//                .when()
//                .get("/api/categories")
//                .then()
//                .statusCode(200)
//                .body(".", hasSize(4));
//    }
//
//    @Test
//    void usingRestClient() {
//        client.get()
//                .uri("/api/categories")
//                .exchange()
//                .expectStatus().isOk();
//    }
//}
