package ru.project.mobile.dto;

import lombok.Data;
import ru.project.mobile.entity.RoleEnum;

@Data
public class ClientUserDto {
    private String username;
    private String email;
    private RoleEnum role;
    private String address;
    private String phone;
}
