# CarRental_FHNW

CarRental_FHNW is a project for car rental management.


---

## Contents

- Analysis
  - Scenario
  - User Stories
  - Use Case
- Design
  - Prototype Design
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

**🚧: Reuse and adapt your own analysis and requirements here.**

### Scenario

CarRental_FHNW is a lightweight demonstration system enabling customer and admin to manage a catalog of rental cars, bookings. It provides both administrative and user-facing functionality.

### User Stories

- As an Admin, I want a consistent visual appearance for easy navigation.
- As an Admin, I want to create, edit, delete, car, bookings and access.
- As an Admin, I want to login as a admin for authentication and authorization.
- As an User, I want to create and update or delete my bookings.
- As a User, I want to view available cars and filter by model, type, fuel type.
- As a User, I want to authenticate to access personal data.
- As a User, I want to see the cost of my booking.


### Use Cases

- **UC-1 [Show filtered car list]:**  
  The user can retrieve a list of all available cars in the system, with options to filter by model, type, or fuel type.
- **UC-2 [Show Car Details]:**  
  The user can view detailed information about a specific car, including its model, type, fuel type, seats, and availability.
- **UC-3 [Manage Cars]:**  
  The Admin can create, update, or delete car entries in the catalog.
- **UC-4 [User Authentication]:**  
  The User or Admin can log in to access personalized or administrative features based on their role.
- **UC-5 [Manage Bookings (User)]:**  
  The user can create, update, or delete their own car bookings.
- **UC-6 [Manage Bookings (Admin)]:**  
  The Admin can view, create, update, or delete any bookings for the user in the system.
- **UC-7 [View Booking Cost]:**  
  The user can view the calculated cost of a booking based on the rental duration.



## Design

**🚧: Decide on your CI, color scheme, graphics, layout, and UX. Add wireframes and diagrams as needed.**

### Wireframe

**🚧: List the main pages: Main Screen, Car choices screen, Login, Admin.**

### Prototype

**🚧: create a prototype using placeholder data.**

### Domain Design

**🚧: Provide an entity-relationship diagram.**

Main domain entities may include: Car, Booking, User, VehicleType.

### Business Logic

**Example:**

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

**🚧: Describe if you used Spring Initializr or adapted dependencies. Document database setup (in-memory/persistent) and placeholder data initialization.**

### Frontend Technology

**🚧: List the frontend framework or app builder used (Budibase). Describe main views and APIs used for each view.**
Budisbase was used as a frontend framework.

**-[Main Screen]**
no api
**-Car choices screen**
Get Car List
**-Login**
 Login API
**My-Bookings (screen)**
Get Booking
Update Booking
Delete booking
**-Admin**
Get Car List
Get Car
Add Car
Update Car
Delete Car
Get Customer List
Get Customer
Add Customer
Update Customer
Delete Customer
Get Booking List
Get Booking
Add Booking
Update Booking
Delete Booking


---

## Execution

**🚧: List steps to run the application and update configurations. For Codespaces or local environments:**

1. Clone the repository.
2. Start the backend (`CarRentalApplication.java`).
3. Open required ports as needed.
4. Deploy or connect the frontend app.
5. Update API endpoints in frontend datasource configuration.


---

## Project Management

### Roles

- Backend Developer: [ Loic Bösch]
- Frontend Developer: [ Jan Heinmann]
- Read.me: [ Varnabakavan Nagarajah]
- 

### Milestones

- Analysis and Requirements
- Prototype and Wireframe
- Domain Model Design
- Business Logic & API Design
- Backend and Data Layer Implementation
- Frontend and Security Integration

---

## Maintainers

- [ Loic Bösch]
- [ Jan Heinmann]
- [ Varnabakavan Nagarajah]
- [ Nils Bumbacher]

---

## License

Apache License, Version 2.0

---
