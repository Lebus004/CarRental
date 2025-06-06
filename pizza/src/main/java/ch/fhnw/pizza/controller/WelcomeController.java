package ch.fhnw.pizza.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
@Hidden // Hide this controller from the Swagger UI
public class WelcomeController {

    @GetMapping(value="/")
    public String getWelcomeString() {
        
        return "Hello, welcome to our Car Rental!";
    }

    @GetMapping(value="/api/user")
    public ResponseEntity<?> getUserRole(Authentication auth) {
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal() == "anonymousUser") {
            return ResponseEntity.status(401).body("Unauthorized: Username or password incorrect");
        }
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String role = userDetails.getAuthorities().toArray()[1].toString();
        return ResponseEntity.ok(role);
    }


}