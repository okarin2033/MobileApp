package ru.project.mobile.entity;

import lombok.Data;
import org.hibernate.mapping.MappedSuperclass;

import javax.persistence.*;

@Entity
@Data
public class UserToken extends DefaultSuperclass {
    @ManyToOne
    private User user;
    private String token;

}
