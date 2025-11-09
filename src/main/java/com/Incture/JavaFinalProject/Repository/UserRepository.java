package com.Incture.JavaFinalProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Incture.JavaFinalProject.Entity.UserModel;
import java.util.Optional;




public interface UserRepository extends JpaRepository<UserModel,Long> {
     Optional<UserModel> findByEmail(String email);
     Boolean existsByEmail(String email);
     boolean existsByid (long id);
}
