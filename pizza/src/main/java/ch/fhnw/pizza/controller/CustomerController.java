package ch.fhnw.pizza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ch.fhnw.pizza.business.service.CustomerService;
import ch.fhnw.pizza.data.domain.Customer;


@RestController
@RequestMapping(path = "/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
        // GET: Hole einen Kunden anhand der ID
        @GetMapping(path = "/customers/{id}", produces = "application/json")
        public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
            try {
                Customer customer = customerService.findCustomerById(id);
                if (customer == null) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
                }
                return ResponseEntity.ok(customer);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }
    
        // GET: Hole alle Kunden
        @GetMapping(path = "/customers", produces = "application/json")
        public ResponseEntity<List<Customer>> getAllCustomers() {
            try {
                List<Customer> customers = customerService.getAllCustomers();
                if (customers.isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No customers found");
                }
                return ResponseEntity.ok(customers);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }
    
        // POST: Füge einen neuen Kunden hinzu
        @PostMapping(path="/customers", consumes="application/json", produces = "application/json")
        public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
            try {
                // Passwort verschlüsseln
                customer.setPassword(passwordEncoder.encode(customer.getPassword()));
                Customer savedCustomer = customerService.addCustomer(customer);
                return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }
    
        // PUT: Aktualisiere einen bestehenden Kunden
        @PutMapping(path = "/customers/{id}", consumes = "application/json", produces = "application/json")
        public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
            try {
                customer.setCustomerId(id.intValue());
                Customer updatedCustomer = customerService.updateCustomer(customer);
                return ResponseEntity.ok(updatedCustomer);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }
    
        // DELETE: Lösche einen Kunden anhand der ID
        @DeleteMapping(path = "/customers/{id}")
        public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
            try {
                Customer deletedCustomer = customerService.deleteCustomer(id);
                if (deletedCustomer == null) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
                }
                return ResponseEntity.ok("Customer with id " + id + " deleted");
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }
    }