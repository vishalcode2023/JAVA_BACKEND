package com.Incture.JavaFinalProject.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Incture.JavaFinalProject.Entity.AdminModel;

public interface AdminRepository extends JpaRepository<AdminModel,Long> {
    Optional<AdminModel> findByEmail(String email);
     Boolean existsByEmail(String email);
}
