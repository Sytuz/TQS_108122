package tqs.cars;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import tqs.cars.repositories.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void whenFindCarById_thenReturnCar() {
        // arrange a new car and insert into db
        tqs.cars.entities.Car car = new tqs.cars.entities.Car("Tesla", "Model S");
        entityManager.persistAndFlush(car); //ensure data is persisted at this point

        // test the query method of interest
        tqs.cars.entities.Car found = carRepository.findById(car.getCarId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getCarId()).isEqualTo(car.getCarId());
    }

    @Test
    public void whenInvalidCarId_thenReturnNull() {
        tqs.cars.entities.Car fromDb = carRepository.findById(-111L).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        tqs.cars.entities.Car car1 = new tqs.cars.entities.Car("Tesla", "Model S");
        tqs.cars.entities.Car car2 = new tqs.cars.entities.Car("Tesla", "Model 3");
        tqs.cars.entities.Car car3 = new tqs.cars.entities.Car("Tesla", "Model X");

        entityManager.persist(car1);
        entityManager.persist(car2);
        entityManager.persist(car3);
        entityManager.flush();

        List<tqs.cars.entities.Car> allCars = carRepository.findAll();

        assertThat(allCars).hasSize(3).extracting(tqs.cars.entities.Car::getModel).containsOnly(car1.getModel(), car2.getModel(), car3.getModel());
    }
}
