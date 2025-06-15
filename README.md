# CarRental_FHNW

CarRental_FHNW is a project for car rental management.

---

## Contents

- Analysis
  - Scenario
  - User Stories
  - Use Case
- Design
  - Wireframe
  - Prototype
  - Domain Design
  - Business Logic
- Implementation
  - Backend Technology
  - Frontend Technology
- Project Management
  - Roles
  - Milestones

---

## Analysis

### Scenario

CarRental_FHNW is a lightweight demonstration system enabling customers and admins to manage a catalog of rental cars and bookings. It provides both administrative and user-facing functionality.

### User Stories

- As an Admin, I want a consistent visual appearance for easy navigation.
- As an Admin, I want to create, edit, and delete cars, bookings, and user access.
- As an Admin, I want to log in as an admin for authentication and authorization.
- As a User, I want to create or delete my bookings.
- As a User, I want to view available cars and filter by model, type, seat, and fuel type.
- As a User, I want to authenticate to access personal data.
- As a User, I want to see the cost of my booking.

### Use Cases

- **UC-1 [Show filtered car list]:**  
  The user can retrieve a list of all available cars in the system, with options to filter by model, type, seats, or fuel type.
- **UC-2 [Show Car Details]:**  
  The user can view detailed information about a specific car, including its model, type, fuel type, seats, and availability.
- **UC-3 [Manage Cars]:**  
  The Admin can create, update, or delete car entries in the catalog.
- **UC-4 [User Authentication]:**  
  The User or Admin can log in to access personalized or administrative features based on their role.
- **UC-5 [Manage Bookings (User)]:**  
  The user can create or delete their own car bookings.
- **UC-6 [Manage Bookings (Admin)]:**  
  The Admin can view, create, or delete any bookings for the user in the system.
- **UC-7 [View Booking Cost]:**  
  The user can view the calculated cost of a booking based on the rental duration.

### Use Case Diagram

The following diagram illustrates the main use cases for the CarRental_FHNW system, highlighting the interactions between Users, Admins, and the system's core functionalities:

![Use Case Diagram](https://github.com/user-attachments/assets/a919cd51-3775-476e-9e1b-b39df2ae8f8b)

**Legend:**
- Solid lines represent direct associations between actors and use cases.
- Dashed arrows with <<Include>> indicate use case inclusion relationships.
- The blue background represents the Car Rental System boundary.

This diagram depicts how Users and Admins interact with features such as viewing and filtering car lists, managing cars, bookings, users, and accessing functionalities like authentication and booking cost calculation.

---

## Design

### Wireframe

- Main Screen
- Car Choices Screen
- Login
- Admin

#### First Wireframe Draft

![Screenshot first draft](https://github.com/Lebus004/CarRental/blob/main/images/2025-06-15%2013_28_14-Internet%20Technologies%20Mockup%20-%20OneNote.png)

### Prototype

- Create a prototype using placeholder data.

### Domain Design

Main domain entities may include: Car, Booking, User, VehicleType.

![ERD2](https://github.com/Lebus004/CarRental/blob/main/images/Bild%20(3).png)

### Business Logic

For booking a car (UC-4):

- **Path:** `/api/bookings`
- **Method:** `POST`, `GET`, `PUT`, `DELETE`
- **Logic:** Users can create, modify, or cancel bookings for available cars. The system checks for overlapping bookings and car availability.

API documentation available at `/swagger-ui.html`.

---

## Implementation

### Backend Technology

- Spring Boot
- Spring Data JPA
- Java Persistence API (JPA)
- H2 Database (in-memory or persistent)

**Dependencies (excerpt):**
```xml
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.3.0</version>
</dependency>
```

- Spring Initializr was used to bootstrap the backend project.
- Database can be configured as in-memory (H2) or persistent using `application.properties`.
- Placeholder/test data can be initialized at application startup.

### Frontend Technology

Budibase was used as the frontend framework.  
[CarRental Budibase App (Login)](
https://fhnwjanheimann.budibase.app/app/bit-group-car-rental)

**Main Views and APIs:**

- **Main Screen:**  
  - No API
- **Car Choices Screen:**  
  - Get Car List
- **Login:**  
  - Login API
- **My-Bookings (screen):**  
  - Get User Booking    
  - Delete User Booking
- **Admin:**  
  - Get Car List  
  - Get Car  
  - Add Car  
  - Update Car  
  - Delete Car  
  - Get Customer List  
  - Get Customer  
  - Add Customer  
  - Update Customer  
  - Delete Customer  
  - Get Booking List  
  - Get Booking  
  - Add Booking  
  - Update Booking  
  - Delete User Booking

---

## Execution

1. Clone the repository.
2. Start the backend (`CarRentalApplication.java`).
3. Open required ports as needed.
4. Deploy or connect the frontend app.
5. Update API endpoints in frontend datasource configuration.

---

## Project Management

### Roles

- Backend Developer: Loic Bösch
- Frontend Developer: Loic Bösch, Varnabakavan Nagarajah
- Budibase Wireframes: Jan Heimann
- Readme: Varnabakavan Nagarajah
- UML Diagram: Nils Bumbacher

### Milestones

- Analysis and Requirements
- Prototype and Wireframe
- Domain Model Design
- Business Logic & API Design
- Backend and Data Layer Implementation
- Frontend and Security Integration

---

## Maintainers

- Loic Bösch
- Jan Heinmann
- Varnabakavan Nagarajah
- Nils Bumbacher

---

## License

Apache License, Version 2.0

---
