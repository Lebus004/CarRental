package ch.fhnw.pizza.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.pizza.data.domain.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByCarModel(String carModel);
    List<Car> findAllByCarTypeContainsIgnoreCase(String carType);
    List<Car> findAllByCarModelContainsIgnoreCase(String carModel);
}
