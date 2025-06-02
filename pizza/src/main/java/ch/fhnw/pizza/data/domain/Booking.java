package ch.fhnw.pizza.data.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Booking {

    @Id
    @JsonIgnore
    private Long bookingId;

    @OneToMany(mappedBy = "menu")
    private List<Car> pizzaList;

    private String currentOffer;

    public Long getBookingId() {
        return bookingId;
    }

    @Column(name = "start_date")
    @JsonFormat(pattern = "HH:mm dd.MM.yyyy") // Format für JSON-Serialisierung/Deserialisierung
    private LocalDateTime startDate;

    @Column(name = "end_date")
    @JsonFormat(pattern = "HH:mm dd.MM.yyyy") // Format für JSON-Serialisierung/Deserialisierung
    private LocalDateTime endDate;

    @Column(name = "booking_cost")
    private Double bookingCost;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // Setters and Getters
    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public List<Car> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(List<Car> pizzaList) {
        this.pizzaList = pizzaList;
    }

    public String getCurrentOffer() {
        return currentOffer;
    }

    public void setCurrentOffer(String currentOffer) {
        this.currentOffer = currentOffer;
    }
    public LocalDateTime getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    public LocalDateTime getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
    public Double getBookingCost() {
        return bookingCost;
    }
    public void setBookingCost(Double bookingCost) {
        this.bookingCost = bookingCost;
    }
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }    
}
