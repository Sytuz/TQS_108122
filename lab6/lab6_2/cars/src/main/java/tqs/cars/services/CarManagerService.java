package tqs.cars.services;

import java.util.Optional;
import java.util.List;
import tqs.cars.entities.Car;

public interface CarManagerService {

    public Car save(Car car);

    public List<Car> getAllCars();

    public Optional<Car> getCarDetails(Long id);

    public List<Car> getCarsByMaker(String maker);

    public List<Car> getCarsByMakerAndModel(String maker, String model);

    public List<Car> getSimilarCars(Car car);

    public Car getMostSimilarCar(Car car);
}
