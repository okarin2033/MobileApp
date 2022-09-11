package ru.project.mobile.entity;

import lombok.Data;
import org.hibernate.mapping.MappedSuperclass;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Data
public class UserToken extends DefaultSuperclass {
    @ManyToOne
    private User user;
    private String token;
}
