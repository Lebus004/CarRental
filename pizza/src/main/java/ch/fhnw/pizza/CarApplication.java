package ch.fhnw.pizza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.pizza.business.service.AdminService;
import ch.fhnw.pizza.business.service.CustomerService;
import ch.fhnw.pizza.business.service.CarService;
import ch.fhnw.pizza.data.domain.Car;
import ch.fhnw.pizza.data.domain.Customer;
import ch.fhnw.pizza.data.domain.Admin;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@RestController
@Hidden // Hide this controller from the Swagger UI
public class CarApplication {

	@Autowired
	private CarService carService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AdminService adminService;


	public static void main(String[] args) {
		SpringApplication.run(CarApplication.class, args);
	}
	

	// Use this method to initialize placeholder data without using Postman
	// If you are persisting data in a file (see application.properties), initializing data that already exists will cause an error during starting the application
	// To resolve the error, delete the file and restart the application
	@PostConstruct
	private void initPlaceholderData() throws Exception {
		Car car = new Car();
		car.setCarType("PW");
		car.setCarModel("VW Golf");
		car.setTypeOfFuel("Diesel");
		car.setSeats(5);
		car.setCarAvailability(true);
		carService.addCar(car);

		car = new Car();
		car.setCarType("SUV");
		car.setCarModel("BMW X5");
		car.setTypeOfFuel("Electric");
		car.setSeats(5);
		car.setCarAvailability(true);
		carService.addCar(car);

		Customer customer = new Customer();
		customer.setUsername("mycustomer");
		customer.setPassword("$2a$10$9fxQtdWuRaYn5UchAm5iAexbPi7tmRadnDogJwXPR9fVDJyt9g/su");
		customerService.addCustomer(customer);

		Admin admin = new Admin();
		admin.setUsername("myadmin");
		admin.setPassword("$2a$10$9fxQtdWuRaYn5UchAm5iAexbPi7tmRadnDogJwXPR9fVDJyt9g/su");
		adminService.addAdmin(admin);
	}

}
