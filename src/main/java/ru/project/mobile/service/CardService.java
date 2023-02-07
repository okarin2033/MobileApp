package ru.project.mobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.mobile.entity.BonusCard;
import ru.project.mobile.service.Authorisation.TokenService;

@Service
public class CardService {
    @Autowired
    TokenService tokenService;
    public BonusCard getUserCard(String token) throws Exception {
        return tokenService.getUserByToken(token).getCard();
    }
}
