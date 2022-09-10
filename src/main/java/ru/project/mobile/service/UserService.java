package ru.project.mobile.service;

import ru.project.mobile.dto.UserDto;
import ru.project.mobile.entity.User;

import java.util.List;

public interface UserService {
    public User addUser(UserDto userDto);
    public boolean removeUser(Long id);
    public User getUserByName(String name) throws Exception;
    public List<User> getUserList();
}
