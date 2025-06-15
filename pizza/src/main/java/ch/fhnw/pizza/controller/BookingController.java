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
import ch.fhnw.pizza.data.domain.Booking;


@RestController
@RequestMapping(path="/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;

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
    public ResponseEntity<Booking> addBooking(@RequestBody Booking booking) {
        // Prüfe, ob das Auto im gewünschten Zeitraum verfügbar ist
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
        // Hole die bestehende Buchung aus der Datenbank
        Booking existingBooking = bookingService.findBookingById(id);
        if (existingBooking == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found");
        }

        // Aktualisiere die Felder der bestehenden Buchung mit den neuen Werten
        existingBooking.setStartDate(booking.getStartDate());
        existingBooking.setEndDate(booking.getEndDate());
        existingBooking.setBookingCost(booking.getBookingCost());
        existingBooking.setCar(booking.getCar());
        existingBooking.setCustomer(booking.getCustomer());

        // Speichere die aktualisierte Buchung
        Booking updatedBooking = bookingService.updateBooking(existingBooking);
        return ResponseEntity.ok(updatedBooking);
    } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
    }
}
}