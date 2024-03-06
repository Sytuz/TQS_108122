package tqs.cars.services;

import org.springframework.stereotype.Service;
import tqs.cars.entities.Car;

import java.util.List;
import java.util.Optional;

@Service
public class CarManagerServiceImpl implements CarManagerService{

    public CarManagerServiceImpl() {
    }

    public Car save(Car car) {
        return null;
    }

    public List<Car> getAllCars() {
        return null;
    }

    public Optional<Car> getCarDetails(Long id) {
        return null;
    }
    
}
