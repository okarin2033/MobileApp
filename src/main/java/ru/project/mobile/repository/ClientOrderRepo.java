package ru.project.mobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.mobile.entity.ClientOrder;
import ru.project.mobile.entity.User;

import java.util.List;

public interface ClientOrderRepo extends JpaRepository<ClientOrder, Long> {
    List<ClientOrder> getClientOrdersByUser(User user);
}