package ch.fhnw.pizza.data.domain;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pizza")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Hidden //This annotation hides the id field from the swagger documentation
    @Column(name = "car_id", nullable = false)
    private Long carId;

    @Column(name = "car_model")
    private String carModel;

    @Column(name = "car_type")
    private String carType;

    @Column(name = "car_availability")
    private Boolean carAvailability;

    @Column(name = "type_of_fuel")
    private String typeOfFuel;

    @Column(name = "seats")
    private Integer seats;

    @ManyToOne
    private Booking menu;

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public Boolean getCarAvailability() {
        return carAvailability;
    }
    
    public void setCarAvailability(Boolean carAvailability) {
        this.carAvailability = carAvailability;
    }

    public String getTypeOfFuel() {
        return typeOfFuel;
    }

    public void setTypeOfFuel(String typeOfFuel) {
        this.typeOfFuel = typeOfFuel;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

}
