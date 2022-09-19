package ru.project.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.project.mobile.dto.LoginFormDto;
import ru.project.mobile.dto.UserDto;
import ru.project.mobile.entity.RoleEnum;
import ru.project.mobile.entity.User;
import ru.project.mobile.repository.UserRepo;
import ru.project.mobile.service.Authorisation.TokenService;
import ru.project.mobile.service.UserServiceImpl;

@RestController
public class UserController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    TokenService tokenService;
    @PostMapping("/register")
    public String addUser(@RequestBody UserDto userDto) throws Exception {
        User user = userService.addUser(userDto);
        String token = tokenService.tokenGenerationWithUser(user);
        return token;
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginFormDto loginFormDto) throws Exception {
        return tokenService.tokenGeneration(loginFormDto);
    }
    @PostMapping("/check")
    public User check(@RequestBody String token) throws Exception {
        return tokenService.getUserByToken(token);
    }
}
