package tqs.cars.services;

import org.springframework.stereotype.Service;
import tqs.cars.entities.Car;
import tqs.cars.repositories.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarManagerServiceImpl implements CarManagerService{

    private final CarRepository carRepository;

    public CarManagerServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarDetails(Long id) {
        return carRepository.findById(id);
    }
    
}
