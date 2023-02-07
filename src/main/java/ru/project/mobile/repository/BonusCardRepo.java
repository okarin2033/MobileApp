package ru.project.mobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.mobile.entity.BonusCard;

public interface BonusCardRepo extends JpaRepository<BonusCard, Long> {
}
