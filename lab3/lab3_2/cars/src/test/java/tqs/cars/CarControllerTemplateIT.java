package tqs.cars;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.TestPropertySource;

import tqs.cars.entities.Car;
import tqs.cars.repositories.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase

public class CarControllerTemplateIT {

    @LocalServerPort
    int randomServerPort;

    // a REST client that is test-friendly
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateCar() {
        Car car = new Car("Opel", "Corsa");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/cars", car, Car.class);

        List<Car> found = repository.findAll();
        assertThat(found).extracting(Car::getMaker).containsOnly("Opel");
    }
    
    @Test
    public void givenCars_whenGetCars_thenStatus200()  {
        createTestCar("Opel", "Corsa");
        createTestCar("Fiat", "Punto");

        ResponseEntity<List<Car>> response = restTemplate.exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2).extracting(Car::getModel).contains("Corsa", "Punto");
    }

    private void createTestCar(String maker, String model) {
        Car car = new Car(maker, model);
        repository.saveAndFlush(car);
    }
}
