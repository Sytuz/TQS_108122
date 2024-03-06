package tqs.cars.services;

import java.util.Optional;
import java.util.List;
import tqs.cars.entities.Car;

public interface CarManagerService {

    public Car save(Car car);

    public List<Car> getAllCars();

    public Optional<Car> getCarDetails(Long id);
}
