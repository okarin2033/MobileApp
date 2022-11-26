package ru.project.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.project.mobile.dto.ClientUserDto;
import ru.project.mobile.dto.LoginFormDto;
import ru.project.mobile.dto.TokenDto;
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
    public TokenDto login(@RequestBody LoginFormDto loginFormDto) throws Exception {
        return tokenService.tokenGeneration(loginFormDto);
    }
    @PostMapping("/verify")
    public ClientUserDto check(@RequestBody TokenDto tokenDto) throws Exception {
        String token = tokenDto.getToken();
        User user = tokenService.getUserByToken(token);
        ClientUserDto clientUserDto = new ClientUserDto();
        clientUserDto.setRole(user.getRole());
        clientUserDto.setUsername(user.getUsername());
        clientUserDto.setEmail(user.getEmail());
        clientUserDto.setAddress(user.getAddress());
        clientUserDto.setPhone(user.getPhone());
        return clientUserDto;
    }
    @PutMapping("update/address")
    public ClientUserDto updateUserAddress(@RequestHeader String token, @RequestBody ClientUserDto dto) throws Exception {
        System.out.println(dto.getAddress());
        User user = tokenService.getUserByToken(token);
        user.setAddress(dto.getAddress());
        userRepo.save(user);
        return check(new TokenDto(token, RoleEnum.USER_ROLE));
    }
    @PutMapping("update/phone")
    public ClientUserDto updateUserPhone(@RequestHeader String token, @RequestBody ClientUserDto dto) throws Exception {
        User user = tokenService.getUserByToken(token);
        user.setPhone(dto.getPhone());
        userRepo.save(user);
        return check(new TokenDto(token, RoleEnum.USER_ROLE));
    }
}
