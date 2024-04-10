package tqs.cars;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import io.restassured.http.ContentType;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import tqs.cars.controllers.CarController;
import tqs.cars.entities.Car;
import tqs.cars.repositories.CarRepository;
import tqs.cars.services.CarManagerService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.junit.jupiter.api.BeforeEach;

import static org.slf4j.LoggerFactory.getLogger;
import org.slf4j.Logger;



import java.util.Arrays;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;

//@WebMvcTest(CarController.class)
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=create")
public class CarControllerRESTAssuredTContainersTest {

    static final Logger log = getLogger(CarControllerRESTAssuredTContainersTest.class);

    Car car1, car2, car3, car4, car5, car6;

    @Autowired
    private CarRepository repository;

    @LocalServerPort
    int randomServerPort;

    @Container
	public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:latest")
		.withUsername("tqs")
		.withPassword("password") 
		.withDatabaseName("cars");
    
    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @BeforeEach
    public void setUpTestCars() throws Exception {
        car1 = repository.save(new Car("Ford", "Focus"));
        car2 = repository.save(new Car("Ford", "Fiesta"));
        car3 = repository.save(new Car("Ford", "Mustang"));
        car4 = repository.save(new Car("Toyota", "Corolla"));
        car5 = repository.save(new Car("Toyota", "Yaris"));
        car6 = repository.save(new Car("Toyota", "Supra"));
    }


    @Test
    @Order(1)
    void whenPostCarthenCreateCar( ) throws Exception {
            given()
                .contentType(ContentType.JSON)
                .body(new Car("Opel", "Corsa"))
                .baseUri("http://localhost:" + randomServerPort)
            .when()
                .post("/api/cars")
            .then()
                .statusCode(201)
                .body("maker", is("Opel"))
                .body("model", is("Corsa"));
    }

    @Test
    @Order(2)
    void givenCarswhenGetCarsthenReturnJsonArray( ) throws Exception {
        given()
            .baseUri("http://localhost:" + randomServerPort)
        .when()
            .get("/api/cars").then().statusCode(200).body("$", hasSize(6));
    }
}
