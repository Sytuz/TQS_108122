package tqs.cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import tqs.cars.entities.Car;
import tqs.cars.repositories.CarRepository;
import tqs.cars.services.CarManagerServiceImpl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    protected static final Logger logger = LogManager.getLogger(CarServiceTest.class);

    @Mock( lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerServiceImpl carService;

    @BeforeEach
    public void setUp() {
        Car car1 = new Car("Tesla", "Model S");
        car1.setCarId(1L);
        Car car2 = new Car("Tesla", "Model 3");
        Car car3 = new Car("Tesla", "Model X");

        List<Car> allCars = Arrays.asList(car1, car2, car3);

        Mockito.when(carRepository.findById(car1.getCarId())).thenReturn(Optional.of(car1));
        Mockito.when(carRepository.findById(car2.getCarId())).thenReturn(Optional.of(car2));
        Mockito.when(carRepository.findById(-99L)).thenReturn(Optional.empty());
        Mockito.when(carRepository.findAll()).thenReturn(allCars);
    }
    
    @Test
    void whenValidId_thenCarShouldBeFound() {
        logger.info(carService.getCarDetails(1L));
        Car found = carService.getCarDetails(1L).get();
        assertThat(found.getCarId()).isEqualTo(1L);
    }

    @Test
    void whenInValidId_thenCarShouldNotBeFound() {
        Optional<Car> found = carService.getCarDetails(-99L);
        assertThat(found.isEmpty());
    }

    @Test
    void given3Cars_whengetAll_thenReturn3Records() {
        Car car1 = new Car(); car1.setMaker("Tesla"); car1.setModel("Model S");
        Car car2 = new Car(); car2.setMaker("Tesla"); car2.setModel("Model 3");
        Car car3 = new Car(); car3.setMaker("Tesla"); car3.setModel("Model X");

        List<Car> allCars = carService.getAllCars();
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findAll();
        assertThat(allCars).hasSize(3).extracting(Car::getModel).contains(car1.getModel(), car2.getModel(), car3.getModel());
    }

    @Test
    void whenSaveCar_thenCarShouldBeReturned() {
        Car car = new Car(); car.setMaker("Tesla"); car.setModel("Model S");
        Mockito.when(carRepository.save(car)).thenReturn(car);
        Car savedCar = carService.save(car);
        assertThat(savedCar).isNotNull();
        assertThat(savedCar.getMaker()).isEqualTo("Tesla");
    }
}
