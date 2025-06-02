package ch.fhnw.pizza.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.pizza.data.domain.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByAdminId(Long adminId);
}