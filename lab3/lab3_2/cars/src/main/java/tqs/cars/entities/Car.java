package tqs.cars.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tqs_car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carId;

    @Column(nullable = false)
    private String maker;

    @Column(nullable = false)
    private String model;

    public Car() {
    }

    public Car(String maker, String model) {
        this.maker = maker;
        this.model = model;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long id) {
        this.carId = id;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return getCarId().equals(car.getCarId()) && getMaker().equals(car.getMaker()) && getModel().equals(car.getModel());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Integer.parseInt(carId.toString());
        hash = 31 * hash + (maker == null ? 0 : maker.hashCode());
        hash = 31 * hash + (model == null ? 0 : model.hashCode());
        return hash;
    }
}
