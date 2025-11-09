package com.Incture.JavaFinalProject.Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Incture.JavaFinalProject.Dto.UpdateResponse;
import com.Incture.JavaFinalProject.Entity.UserModel;
import com.Incture.JavaFinalProject.Exception.CustomException;
import com.Incture.JavaFinalProject.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserModel getuserbyId(long id) {
        UserModel userModel =  userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User Not Found..!"));
        userModel.setPassword(null);
        return userModel;
    }

    public UserModel updateuserById(long id, UpdateResponse updateResponse) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User Not Found..!"));

        if (updateResponse.getName() != null && !updateResponse.getName().isEmpty()) {
            user.setEmail(updateResponse.getName());
        }

        if (updateResponse.getPassword() != null && !updateResponse.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updateResponse.getPassword()));
        }

        userRepository.save(user);
        return user;
    }

    public String deleteUserbyId(long id) {
        if (!userRepository.existsByid(id)) {
            throw new CustomException("User not Found..!");
        }

        userRepository.deleteById(id);
        return "User Deleted successfully";
    }
}
