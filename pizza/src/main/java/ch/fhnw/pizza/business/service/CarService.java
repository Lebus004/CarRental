package ch.fhnw.pizza.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.pizza.data.domain.Booking;
import ch.fhnw.pizza.data.domain.Car;
import ch.fhnw.pizza.data.repository.CarRepository;

@Service
public class CarService {

    @Autowired
    private CarRepository pizzaRepository;

    public Car findPizzaById(Long carId) {
        try {
            Car pizza = pizzaRepository.findById(carId).get();
            return pizza;
        } catch (Exception e) {
            throw new RuntimeException("Pizza with id " + carId + " not found");
        }
    }

    public List<Car> getAllPizzas() {
        List<Car> pizzaList = pizzaRepository.findAll();
        return pizzaList;
    }

    public Car addPizza(Car pizza) throws Exception {
        if(pizza.getCarType() != null) {
            if (pizzaRepository.findByCarType(pizza.getCarType()) == null)
                return pizzaRepository.save(pizza);
            throw new Exception("Pizza " + pizza.getCarType() + " already exists");
        }
        throw new Exception("Invalid pizza name ");
    }

    public Car updatePizza(Long carId, Car pizza) throws Exception {
        Car pizzaToUpdate = pizzaRepository.findById(carId).get();
        if(pizzaToUpdate != null) {
            if(pizza.getCarType() != null)
                pizzaToUpdate.setCarType(pizza.getCarType());
            if(pizza.getCarModel() != null)
                pizzaToUpdate.setCarModel(pizza.getCarModel());
            return pizzaRepository.save(pizzaToUpdate);
        }
        throw new Exception("Pizza with id " + carId + " does not exist");
    }

    public void deletePizza(Long carId) throws Exception {
        if(pizzaRepository.existsById(carId)) {
            pizzaRepository.deleteById(carId);
        } else
            throw new Exception("Pizza with id " + carId + " does not exist");
    }

    //Business Logic to get current offer according to the location of the user requesting the menu
    private String getCurrentOffer(String location) {
        String currentOffer = "No special offer for your location. Do check back again.";
        if("Basel".equalsIgnoreCase(location))
            currentOffer = "10% off on all large pizzas!!!";
        else if("Brugg".equalsIgnoreCase(location))
            currentOffer = "Two for the price of One on all small pizzas!!!";
        return currentOffer;
    }

    public Booking getMenuByLocation(String location) {
        String currentOffer = getCurrentOffer(location);
        List<Car> pizzaList = getAllPizzas();
        Booking menu = new Booking();
        menu.setPizzaList(pizzaList);
        menu.setCurrentOffer(currentOffer);
        return menu;
    }

        
}
