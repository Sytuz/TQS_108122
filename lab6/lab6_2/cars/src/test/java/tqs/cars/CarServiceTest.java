package tqs.cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        car2.setCarId(2L);
        Car car3 = new Car("Tesla", "Model X");
        car3.setCarId(3L);

        List<Car> allCars = Arrays.asList(car1, car2, car3);

        Mockito.when(carRepository.findById(car1.getCarId())).thenReturn(Optional.of(car1));
        Mockito.when(carRepository.findById(car2.getCarId())).thenReturn(Optional.of(car2));
        Mockito.when(carRepository.findById(-99L)).thenReturn(Optional.empty());
        Mockito.when(carRepository.findAll()).thenReturn(allCars);

        Mockito.when(carRepository.save(car1)).thenReturn(car1);

        Mockito.when(carRepository.findByMaker(car1.getMaker())).thenReturn(Arrays.asList(car1, car2, car3));

        Mockito.when(carRepository.findByMakerAndModel(car1.getMaker(), car1.getModel())).thenReturn(Arrays.asList(car1));

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
        assertTrue(found.isEmpty());
    }

    @Test
    void given3Cars_whengetAll_thenReturn3Records() {
        Car car1 = new Car("Tesla", "Model S"); car1.setCarId(1L);
        Car car2 = new Car("Tesla", "Model 3"); car2.setCarId(2L);
        Car car3 = new Car("Tesla", "Model X"); car3.setCarId(3L);

        List<Car> allCars = carService.getAllCars();
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findAll();
        assertThat(allCars).hasSize(3).extracting(Car::getModel).contains(car1.getModel(), car2.getModel(), car3.getModel());
    }

    @Test
    void whenSaveCar_thenCarShouldBeReturned() {
        Car car = new Car("Tesla", "Model S"); car.setCarId(1L);
        Mockito.when(carRepository.save(car)).thenReturn(car);
        Car savedCar = carService.save(car);
        assertThat(savedCar).isNotNull();
        assertThat(savedCar.getMaker()).isEqualTo("Tesla");
    }

    @Test
    void checkMostSimilarCar() {
        Car car = new Car("Tesla", "Model S"); car.setCarId(1L);
        Car car2 = new Car("Tesla", "Model 3"); car2.setCarId(2L);

        Car mostSimilarCar = carService.getMostSimilarCar(car);
        assertThat(mostSimilarCar).isEqualTo(car2);
    }

    @Test
    void checkSimilarCars() {
        Car car = new Car("Tesla", "Model S"); car.setCarId(1L);
        Car car2 = new Car("Tesla", "Model 3"); car2.setCarId(2L);
        Car car3 = new Car("Tesla", "Model X"); car3.setCarId(3L);
        Car car4 = new Car("Opel", "Corsa"); car4.setCarId(4L);

        List<Car> similarCars = carService.getSimilarCars(car);
        assertThat(similarCars).hasSize(2).contains(car2, car3).doesNotContain(car4);
    }

    @Test
    void checkCarsByMaker() {
        Car car = new Car("Tesla", "Model S"); car.setCarId(1L);
        Car car2 = new Car("Tesla", "Model 3"); car2.setCarId(2L);
        Car car3 = new Car("Tesla", "Model X"); car3.setCarId(3L);
        Car car4 = new Car("Opel", "Corsa"); car4.setCarId(4L);

        List<Car> carsByMaker = carService.getCarsByMaker(car.getMaker());
        assertThat(carsByMaker).hasSize(3).contains(car, car2, car3).doesNotContain(car4);
    }

    @Test
    void checkCarsByMakerAndModel() {
        Car car = new Car("Tesla", "Model S"); car.setCarId(1L);
        Car car2 = new Car("Tesla", "Model 3"); car2.setCarId(2L);
        Car car3 = new Car("Tesla", "Model X"); car3.setCarId(3L);

        Mockito.when(carRepository.findByMakerAndModel(car.getMaker(), car.getModel())).thenReturn(Arrays.asList(car));

        List<Car> carsByMakerAndModel = carService.getCarsByMakerAndModel(car.getMaker(), car.getModel());
        assertThat(carsByMakerAndModel).hasSize(1).contains(car);
    }
}
