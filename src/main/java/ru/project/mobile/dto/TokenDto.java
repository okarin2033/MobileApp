package ru.project.mobile.dto;

import lombok.Data;
import ru.project.mobile.entity.RoleEnum;

@Data
public class TokenDto {
    String token;
    RoleEnum role;
    public TokenDto(String token, RoleEnum role) {
        this.token = token;
        this.role = role;
    }
}
