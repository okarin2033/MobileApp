package ru.project.mobile.entity;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="users")
public class User extends DefaultSuperclass{
    private String username;
    private String password;
    private String email;

    @Enumerated
    private RoleEnum role;

    @Override
    void preInsert() {
        if (this.getActive() == null)
            this.setActive(true);
        if (this.getRole() == null){
            role = RoleEnum.USER_ROLE;
        }
        this.setDateCreated(new Date());
    }
}
