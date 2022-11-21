package ru.project.mobile.entity;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Map;

@Entity
@Data
public class ClientOrder extends DefaultSuperclass{
    @ManyToOne
    User user;
    @ElementCollection
    Map<String, Double> bookList;
    double fullPrice;

}
