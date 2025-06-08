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

import ch.fhnw.pizza.business.service.BookingService;
import ch.fhnw.pizza.business.service.CarService;
import ch.fhnw.pizza.business.service.CustomerService;
import ch.fhnw.pizza.data.domain.Booking;
import ch.fhnw.pizza.data.domain.Car;
import ch.fhnw.pizza.data.domain.Customer;


@RestController
@RequestMapping(path="/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CarService carService;

    @Autowired
    private CustomerService customerService;

    @GetMapping(path="/bookings/{id}", produces = "application/json")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id) {
        try{
            Booking booking = bookingService.findBookingById(id);
            return ResponseEntity.ok(booking);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(path="/bookings", produces = "application/json")
    public List<Booking> getBookingList() {
        List<Booking> bookingList = bookingService.getAllBookings();
        if(bookingList.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No bookings found");
        return bookingList;
    }

    // Booking costs are displayed weirdly rather than actual costs
    @PostMapping(path="/bookings", consumes="application/json", produces = "application/json")
    public ResponseEntity<Booking> addBooking(@RequestBody BookingDto bookingDto) {
        // Hole Car und Customer anhand der IDs
        Car car = carService.findCarById(bookingDto.carId);
        Customer customer = customerService.findCustomerById(bookingDto.customerId);

        Booking booking = new Booking();
        booking.setStartDate(bookingDto.startDate);
        booking.setDuration(bookingDto.duration != null ? bookingDto.duration.intValue() : null);
        booking.setCar(car);
        booking.setCustomer(customer);
        booking.setEndDate(bookingDto.startDate.plusHours(bookingDto.duration));

        // Prüfe, ob das Startdatum in der Vergangenheit liegt
        if (booking.getStartDate() == null || booking.getDuration() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "startDate and duration are required");
        }
        if (booking.getStartDate().isBefore(java.time.LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No bookings in the past allowed");
        }

        boolean available = bookingService.isCarAvailable(
            booking.getCar().getCarId(),
            booking.getStartDate(),
            booking.getEndDate()
        );
        if (!available) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Car is not available in the selected period");
        }
        try {
            booking = bookingService.addBooking(booking);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return ResponseEntity.ok(booking);
    }

    @DeleteMapping(path="/bookings/{id}", produces = "application/json")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
        Booking booking = null;
        try{
            booking = bookingService.deleteBooking(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.ok("Booking with id " + id + " deleted");
    }

    // Creates new booking rather than updating the last one
    @PutMapping(path="/bookings/{id}", consumes="application/json", produces = "application/json")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
    try {
        Booking existingBooking = bookingService.findBookingById(id);
        if (existingBooking == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found");
        }

        existingBooking.setStartDate(booking.getStartDate());
        existingBooking.setDuration(booking.getDuration());
        existingBooking.setEndDate(booking.getStartDate().plusHours(booking.getDuration()));
        existingBooking.setCar(booking.getCar());
        existingBooking.setCustomer(booking.getCustomer());

        // Kosten neu berechnen
        long hours = existingBooking.getDuration();
        int price = BookingService.HOURLY_PRICES.getOrDefault((int) hours, -1);
        if (price == -1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "No price defined for this duration");
        }
        existingBooking.setBookingCost((double) price);

        Booking updatedBooking = bookingService.updateBooking(existingBooking);
        return ResponseEntity.ok(updatedBooking);
    } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
    }
}
}