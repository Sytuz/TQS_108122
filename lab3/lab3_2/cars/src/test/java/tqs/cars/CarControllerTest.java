package tqs.cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tqs.cars.controllers.CarController;
import tqs.cars.entities.Car;
import tqs.cars.services.CarManagerService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(CarController.class)
public class CarControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @Test
    void whenPostCar_thenCreateCar( ) throws Exception {
        Car someCar = new Car("Opel", "Corsa");

        when( service.save(Mockito.any()) ).thenReturn(someCar);

        mvc.perform(
                post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(someCar)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("Opel")))
                .andExpect(jsonPath("$.model", is("Corsa")));

        verify(service, times(1)).save(Mockito.any());

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

        mvc.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].maker", is(someCar.getMaker())))
                .andExpect(jsonPath("$[1].maker", is(otherCar.getMaker())));

        verify(service, times(1)).getAllCars();
    }
}
