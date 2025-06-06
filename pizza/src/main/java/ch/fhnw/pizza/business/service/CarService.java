package ch.fhnw.pizza.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.pizza.data.domain.Car;
import ch.fhnw.pizza.data.repository.CarRepository;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Car findCarById(Long carId) {
        return carRepository.findById(carId).orElse(null);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car findCarByCarModel(String carModel) {
        return carRepository.findByCarModel(carModel);
    }

    public List<Car> findAllByCarTypeContainsIgnoreCase(String carType) {
        return carRepository.findAllByCarTypeContainsIgnoreCase(carType);
    }

    public Car addCar(Car car) throws Exception {
        if (car.getCarModel() == null || car.getCarType() == null) {
            throw new Exception("Car model and type cannot be null");
        }
        return carRepository.save(car);
    }

    public Car deleteCar(Long carId) {
        Car car = findCarById(carId);
        if (car != null) {
            carRepository.delete(car);
        }
        return car;
    }

    public Car updateCar(Car car) {
        return carRepository.save(car);
    }

    public List<Car> filterCars(String carModel, String carType, Boolean carAvailability, String typeOfFuel, Integer seats) {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
            .filter(car -> carModel == null || car.getCarModel().equalsIgnoreCase(carModel))
            .filter(car -> carType == null || car.getCarType().equalsIgnoreCase(carType))
            .filter(car -> carAvailability == null || car.getCarAvailability().equals(carAvailability))
            .filter(car -> typeOfFuel == null || car.getTypeOfFuel().equalsIgnoreCase(typeOfFuel))
            .filter(car -> seats == null || car.getSeats().equals(seats))
            .toList();
    }
}