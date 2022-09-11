package ru.project.mobile.service.Authorisation;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.mobile.dto.LoginFormDto;
import ru.project.mobile.dto.UserDto;
import ru.project.mobile.entity.User;
import ru.project.mobile.entity.UserToken;
import ru.project.mobile.repository.TokenRepo;
import ru.project.mobile.repository.UserRepo;
import ru.project.mobile.service.UserService;
import ru.project.mobile.service.UserServiceImpl;

import java.security.SecureRandom;
import java.util.Arrays;

@Service
public class TokenService {
    final TokenRepo tokenRepo;
    final UserRepo userRepo;
    final UserService userService;
    @Autowired
    public TokenService(TokenRepo tokenRepo, UserRepo userRepo, UserServiceImpl userService) {
        this.tokenRepo = tokenRepo;
        this.userRepo = userRepo;
        this.userService = userService;
    }

    public String tokenGeneration(LoginFormDto loginFormDto) throws Exception {
        byte[] bytes = new byte[20];
        User tokenCandidate = userRepo.findByUsername(loginFormDto.getUsername());
        if (tokenCandidate==null)
            throw new Exception("User Not Found") ;
        if (tokenCandidate.getPassword().equals(loginFormDto.getPassword())) {        //ADD SECURITY
            SecureRandom.getInstanceStrong().nextBytes(bytes);
            String token = Arrays.toString(bytes);
            UserToken session= new UserToken();
            session.setToken(token);
            session.setUser(tokenCandidate);
            UserToken authorisedSession = tokenRepo.save(session);
            return authorisedSession.getToken();
        } else {
            throw new Exception("Wrong Password") ;
        }
    }

    public String userRegistration(UserDto userDto) throws Exception {
        User user = userService.addUser(userDto);
        LoginFormDto dto = new LoginFormDto();
        dto.setPassword(user.getPassword());
        dto.setUsername(user.getUsername());
        return tokenGeneration(dto);
    }

    public User getUserByToken(String token) throws Exception {
        User user = tokenRepo.getUserTokenByToken(token).getUser();
        if (user == null)
            throw new Exception("Wrong Token");
        else if (tokenRepo.getUserTokenByToken(token).getActive().equals(true))
            return user;
        else throw new Exception("Wrong Token");
    }
}
