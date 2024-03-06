package tqs.cars.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import tqs.cars.entities.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    public Car findByCarId(Long id);

    public List<Car> findByMaker(String maker);

    public List<Car> findByMakerAndModel(String maker, String model);
}
