package ch.fhnw.pizza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ch.fhnw.pizza.business.service.CarService;
import ch.fhnw.pizza.data.domain.Car;

@RestController
@RequestMapping(path="/api")
public class CarController {

@Autowired
private CarService carService;

@GetMapping(path="/cars/{id}", produces = "application/json")
public ResponseEntity<Car> getCar(@PathVariable Long id) {
    try{
        Car car = carService.findCarById(id);
        return ResponseEntity.ok(car);
    }
    catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
}

@GetMapping(path="/cars", produces = "application/json")
public List<Car> getCarList() {
    List<Car> carList = carService.getAllCars();
    if(carList.isEmpty())
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No cars found");
    return carList;
}
@PostMapping(path="/cars", consumes="application/json", produces = "application/json")
public ResponseEntity<Car> addCar(@RequestBody Car car) {
    try{
        car = carService.addCar(car);
    } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
    }
    return ResponseEntity.ok(car);
    
}

@DeleteMapping(path="/cars/{id}")
public ResponseEntity<String> deleteCar(@PathVariable Long id) {
    try {
        carService.deleteCar(id);
    } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
    return ResponseEntity.ok("Car with id " + id + " deleted");
}

// update car
@PutMapping(path="/cars/{id}", consumes="application/json", produces = "application/json")
public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) {
    try {
        // Hole das bestehende Auto aus der Datenbank
        Car existingCar = carService.findCarById(id);
        if (existingCar == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found");
        }

        // Aktualisiere die Felder des bestehenden Autos mit den neuen Werten
        existingCar.setCarModel(car.getCarModel());
        existingCar.setCarType(car.getCarType());
        existingCar.setCarAvailability(car.getCarAvailability());
        existingCar.setTypeOfFuel(car.getTypeOfFuel());
        existingCar.setSeats(car.getSeats());

        // Speichere das aktualisierte Auto
        Car updatedCar = carService.updateCar(existingCar);
        return ResponseEntity.ok(updatedCar);
    } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}

}