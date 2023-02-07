package ru.project.mobile.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
@Data
public class BonusCard extends DefaultSuperclass{
    int bonusValue;
    int cardPower;
}
