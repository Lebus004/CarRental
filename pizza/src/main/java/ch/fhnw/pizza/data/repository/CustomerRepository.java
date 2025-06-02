package ch.fhnw.pizza.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.pizza.data.domain.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCustomerId(Integer customerId); // Methodennamen geändert
}