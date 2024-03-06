package tqs.cars.services;

import org.hibernate.mapping.Set;
import org.springframework.stereotype.Service;
import tqs.cars.entities.Car;
import tqs.cars.repositories.CarRepository;

import java.util.ArrayList;
import java.util.Iterator;
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
    
    public List<Car> getCarsByMaker(String maker) {
        return carRepository.findByMaker(maker);
    }

    public List<Car> getCarsByMakerAndModel(String maker, String model) {
        return carRepository.findByMakerAndModel(maker, model);
    }

    public List<Car> getSimilarCars(Car car) {
        List<Car> otherCars = new ArrayList<>(carRepository.findByMaker(car.getMaker()));

        Iterator<Car> iterator = otherCars.iterator();
        while (iterator.hasNext()) {
            Car otherCar = iterator.next();
            if (otherCar.equals(car)) {
                iterator.remove();
            }
        }

        return otherCars;
    }

    public Car getMostSimilarCar(Car car) {
        List<Car> cars = new ArrayList<>(carRepository.findByMakerAndModel(car.getMaker(), car.getModel()));

        if (!cars.isEmpty()) {
            cars.remove(car);
            if (!cars.isEmpty()) {
                return cars.get(0);
            }
        }

        List<Car> otherCars = new ArrayList<>(carRepository.findByMaker(car.getMaker()));

        otherCars.remove(car);
        if (!otherCars.isEmpty()) {
            return otherCars.get(0);
        }

        return null;
    }

}
