package ru.project.mobile.controller;


import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.mobile.dto.LoginFormDto;

@RestController
public class TestController {
    @GetMapping("/test")
    public LoginFormDto testGet(){
        LoginFormDto dto = new LoginFormDto();
        dto.setUsername("test_user");
        dto.setPassword("test_pass");
        return dto;
    }
}
