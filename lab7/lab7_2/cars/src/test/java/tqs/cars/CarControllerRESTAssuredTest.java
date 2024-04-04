package tqs.cars;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tqs.cars.controllers.CarController;
import tqs.cars.entities.Car;
import tqs.cars.services.CarManagerService;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
public class CarControllerRESTAssuredTest {
    
    @MockBean
    private CarManagerService service;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void whenPostCar_thenCreateCar( ) throws Exception {
        Car someCar = new Car("Opel", "Corsa");

        when( service.save(Mockito.any()) ).thenReturn(someCar);
        
        RestAssuredMockMvc
            .given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new Car("Opel", "Corsa"))
            .when()
                .post("/api/cars")
            .then()
                .statusCode(201)
                .body("maker", is("Opel"))
                .body("model", is("Corsa"));
    }

    @Test
    void givenCars_whenGetCars_thenReturnJsonArray( ) throws Exception {
        Car someCar = new Car();
        someCar.setMaker("Opel");
        someCar.setModel("Corsa");

        Car otherCar = new Car();
        otherCar.setMaker("Fiat");
        otherCar.setModel("Punto");

        List<Car> allCars = Arrays.asList(someCar, otherCar);

        when( service.getAllCars() ).thenReturn(allCars);

        RestAssuredMockMvc
            .given()
            .when()
                .get("/api/cars")
            .then()
                .statusCode(200)
                .body("$", hasSize(2))
                .body("[0].maker", is(someCar.getMaker()))
                .body("[1].maker", is(otherCar.getMaker()));
    }
}
