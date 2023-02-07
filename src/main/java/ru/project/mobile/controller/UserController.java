package ru.project.mobile.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.project.mobile.dto.*;
import ru.project.mobile.entity.BonusCard;
import ru.project.mobile.entity.RoleEnum;
import ru.project.mobile.entity.User;
import ru.project.mobile.repository.BonusCardRepo;
import ru.project.mobile.repository.UserRepo;
import ru.project.mobile.service.Authorisation.TokenService;
import ru.project.mobile.service.CardService;
import ru.project.mobile.service.UserServiceImpl;

@RestController
public class UserController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    TokenService tokenService;
    @Autowired
    BonusCardRepo bonusCardRepo;
    @Autowired
    CardService cardService;
    @PostMapping("/register")
    public String addUser(@RequestBody UserDto userDto) throws Exception {
        User user = userService.addUser(userDto);
        String token = tokenService.tokenGenerationWithUser(user);
        return token;
    }
    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginFormDto loginFormDto) throws Exception {
        return tokenService.tokenGeneration(loginFormDto);
    }
    @PostMapping("/verify")
    public ClientUserDto check(@RequestBody TokenDto tokenDto) throws Exception {
        String token = tokenDto.getToken();
        User user = tokenService.getUserByToken(token);
        ClientUserDto clientUserDto = new ClientUserDto();
        clientUserDto.setRole(user.getRole());
        clientUserDto.setUsername(user.getUsername());
        clientUserDto.setEmail(user.getEmail());
        clientUserDto.setAddress(user.getAddress());
        clientUserDto.setPhone(user.getPhone());
        return clientUserDto;
    }
    @PutMapping("update/address")
    public ClientUserDto updateUserAddress(@RequestHeader String token, @RequestBody ClientUserDto dto) throws Exception {
        System.out.println(dto.getAddress());
        User user = tokenService.getUserByToken(token);
        user.setAddress(dto.getAddress());
        userRepo.save(user);
        return check(new TokenDto(token, RoleEnum.USER_ROLE));
    }
    @PutMapping("update/phone")
    public ClientUserDto updateUserPhone(@RequestHeader String token, @RequestBody ClientUserDto dto) throws Exception {
        User user = tokenService.getUserByToken(token);
        user.setPhone(dto.getPhone());
        userRepo.save(user);
        return check(new TokenDto(token, RoleEnum.USER_ROLE));
    }

    @SneakyThrows
    @GetMapping("card/get")
    public CardDto getCardInfo(@RequestHeader String token) {
        BonusCard card;
        try{
            card= cardService.getUserCard(token);
            if (card==null){
                BonusCard newCard = new BonusCard();
                newCard.setBonusValue(50);
                newCard.setCardPower(3);
                card = bonusCardRepo.save(newCard);
                User user = tokenService.getUserByToken(token);
                user.setCard(card);
                userRepo.save(user);
            }
        }
        catch (Exception e) {
            card=new BonusCard();
            e.printStackTrace();
        }
        CardDto cardDto= new CardDto();
        cardDto.setBonus(card.getBonusValue());
        cardDto.setPower(card.getCardPower());
        return cardDto;
    }

    @SneakyThrows
    @PutMapping("card/put")
    public CardDto updateCard(@RequestHeader String token, @RequestBody CardDto cardDto) {
        BonusCard card;
        try{
            card= cardService.getUserCard(token);
            if (card==null){
                BonusCard newCard = new BonusCard();
                newCard.setBonusValue(50);
                newCard.setCardPower(3);
                card = bonusCardRepo.save(newCard);
                User user = tokenService.getUserByToken(token);
                user.setCard(card);
                userRepo.save(user);
            }
        }
        catch (Exception e) {
            card=new BonusCard();
            e.printStackTrace();
        }
        card.setCardPower(cardDto.getPower());
        card.setBonusValue(cardDto.getBonus());
        bonusCardRepo.save(card);
        CardDto dto = new CardDto();
        dto.setBonus(card.getBonusValue());
        dto.setPower(card.getCardPower());
        return dto;
    }
}
