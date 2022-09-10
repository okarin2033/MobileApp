package ru.project.mobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import ru.project.mobile.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
