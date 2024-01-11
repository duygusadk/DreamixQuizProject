package com.example.demo.controller;


import com.example.demo.entity.Users;
import com.example.demo.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Users> findAllUsers(){
        return userService.findAllUsers();
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody Users user){
        try {
            userService.save(user);
            return new ResponseEntity<>("The user is created", HttpStatus.CREATED);
        }catch (Exception e ){
            return new ResponseEntity<>("The user is not created",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody @NonNull Users updatedUser) {
         try {
             userService.update(updatedUser, id);
             return new ResponseEntity<>("The user is updated", HttpStatus.OK);
         }catch (Exception e){
             return  new ResponseEntity<>("The user is not updated",HttpStatus.BAD_REQUEST);
         }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        try {
            userService.deleteById(id);
            return new ResponseEntity<>("The user is deleted", HttpStatus.OK);
        }catch(Exception e){
           return  new ResponseEntity<>("The user is not deleted",HttpStatus.BAD_REQUEST) ;
        }
    }


}
