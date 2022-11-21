package ru.project.mobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.mobile.entity.ClientOrder;

public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long> {
}