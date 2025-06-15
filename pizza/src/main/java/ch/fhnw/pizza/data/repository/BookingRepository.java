package ch.fhnw.pizza.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.pizza.data.domain.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByBookingId(Integer bookingId); // Methodennamen geändert
    List<Booking> findByCar_CarId(Long carId);
    List<Booking> findByCustomer_CustomerId(Integer customerId);
}