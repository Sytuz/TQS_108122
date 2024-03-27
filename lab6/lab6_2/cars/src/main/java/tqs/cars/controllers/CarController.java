package tqs.cars.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tqs.cars.entities.Car;
import tqs.cars.services.CarManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
public class CarController {

    private final CarManagerService carService;

    /**
     * Using constructor Injection instead of @autowired
     * when using a constructor to set injected properties, you do not have to provide the autowire annotation
     * @param carService
     */
    public CarController(CarManagerService carService) {
        this.carService = carService;
    }

    @PostMapping("/cars" )
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        HttpStatus status = HttpStatus.CREATED;
        Car saved = carService.save(car);
        return new ResponseEntity<>(saved, status);
    }

    @GetMapping(path="/cars" )
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping(path="/cars/{id}" )
    public ResponseEntity<Car> getCarById(@PathVariable("id") Long id) {
        return carService.getCarDetails(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
}
