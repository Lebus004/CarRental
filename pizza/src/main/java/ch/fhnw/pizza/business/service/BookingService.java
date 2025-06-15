package ch.fhnw.pizza.business.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.pizza.data.domain.Booking;
import ch.fhnw.pizza.data.repository.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking findBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking addBooking(Booking booking) throws Exception {
        if (booking.getCar() == null || booking.getCustomer() == null) {
            throw new Exception("Car and User cannot be null");
        }
        long hours = booking.getDuration();
        if (hours < 1 || hours > 12) {
            throw new Exception("Booking duration must be between 1 and 12 hours");
        }
        int price = HOURLY_PRICES.getOrDefault((int) hours, -1);
        if (price == -1) {
            throw new Exception("No price defined for this duration");
        }
        booking.setBookingCost((double) price);
        // endDate wird im Controller gesetzt!
        return bookingRepository.save(booking);
    }

    public Booking deleteBooking(Long bookingId) {
        Booking booking = findBookingById(bookingId); // Variablenname angepasst
        if (booking != null) {
            bookingRepository.delete(booking);
        }
        return booking;
    }

    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking findBookingByBookingId(Integer bookingId) {
        return bookingRepository.findByBookingId(bookingId); // Methodennamen angepasst
    }

    // Beispielmethode im BookingService
    public boolean isCarAvailable(Long carId, LocalDateTime start, LocalDateTime end) {
        List<Booking> bookings = bookingRepository.findByCar_CarId(carId);
        for (Booking b : bookings) {
            // Prüfe auf Überschneidung der Zeiträume
            if (!(end.isBefore(b.getStartDate()) || start.isAfter(b.getEndDate()))) {
                return false; // Überschneidung gefunden
            }
        }
        return true;
    }

    public boolean isStartDateInPast(Booking booking) {
        return booking.getStartDate().isBefore(java.time.LocalDateTime.now());
    }

    public List<Booking> findBookingsByCustomerId(Integer customerId) {
        return bookingRepository.findByCustomer_CustomerId(customerId);
    }

    public static final Map<Integer, Integer> HOURLY_PRICES = Map.ofEntries(
        Map.entry(1, 100),
        Map.entry(2, 195),
        Map.entry(3, 290),
        Map.entry(4, 385),
        Map.entry(5, 475),
        Map.entry(6, 570),
        Map.entry(7, 660),
        Map.entry(8, 750),
        Map.entry(9, 840),
        Map.entry(10, 930),
        Map.entry(11, 1020),
        Map.entry(12, 1050)
    );
}