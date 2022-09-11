package ru.project.mobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.mobile.entity.User;
import ru.project.mobile.entity.UserToken;

public interface TokenRepo extends JpaRepository<UserToken, Long> {
    UserToken getUserTokenByToken(String token);
}
