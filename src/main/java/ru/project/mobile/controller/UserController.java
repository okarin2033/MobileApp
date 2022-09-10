package ru.project.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.mobile.entity.User;
import ru.project.mobile.repository.UserRepo;

@RestController
public class UserController {
    @Autowired
    UserRepo userRepo;
    @GetMapping("/test")
    public User addUser(){
        User user= new User();
        return userRepo.save(user);
    }
}
