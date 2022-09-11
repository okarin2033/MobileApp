package ru.project.mobile.entity;

import lombok.Data;
import org.hibernate.mapping.MappedSuperclass;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class UserToken extends DefaultSuperclass {
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    String token;
}
