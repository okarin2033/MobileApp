package ru.project.mobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.project.mobile.dto.UserDto;
import ru.project.mobile.entity.BonusCard;
import ru.project.mobile.entity.User;
import ru.project.mobile.repository.UserRepo;
import ru.project.mobile.service.Authorisation.TokenService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;
    @Override
    public User addUser(UserDto userDto) {
        User user= new User();
     //   System.out.println(userDto.getUsername());
        user.setPassword(userDto.getPassword()); //ADD SECURITY!!!
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        return userRepo.save(user);
    }

    @Override
    public boolean removeUser(Long id) {
        try {
            userRepo.deleteById(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserByName(String name) throws Exception {
            User user = userRepo.findByUsername(name);
            if (user == null) throw new Exception("User not found in getUserByName");
            return user;
    }

    @Override
    public List<User> getUserList() {
        return userRepo.findAll();
    }

}
