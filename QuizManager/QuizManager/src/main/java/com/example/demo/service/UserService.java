package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers(){
        return  userRepository.findAll();

    }
    public User findUserById(Long id){

        return userRepository.findById(id).get();
    }
    public User save(User user){
        return userRepository.save(user);
    }

    public User update(User user, Long id) {
        User updatedUser=userRepository.findById(id).get();
        if(updatedUser.getId()==null){throw new NoSuchElementException();
        }
        updatedUser.setFName(user.getFName());
        updatedUser.setLName(user.getLName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());


        return  userRepository.save(updatedUser);
    }
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
