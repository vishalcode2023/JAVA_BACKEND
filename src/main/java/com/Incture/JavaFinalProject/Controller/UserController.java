package com.Incture.JavaFinalProject.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Incture.JavaFinalProject.Dto.UpdateResponse;
import com.Incture.JavaFinalProject.Entity.UserModel;
import com.Incture.JavaFinalProject.Services.UserServices;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class UserController {

    private final UserServices userServices;

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getuserbyId(@PathVariable long id) {
        UserModel response = userServices.getuserbyId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateuserById(@PathVariable long id, UpdateResponse updateResponse) {
        UserModel userModel = userServices.updateuserById(id, updateResponse);
        return ResponseEntity.ok(userModel);
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> deleteuser(@PathVariable long id){
        String userModel = userServices.deleteUserbyId(id);
        return ResponseEntity.ok(userModel);
    }

}
