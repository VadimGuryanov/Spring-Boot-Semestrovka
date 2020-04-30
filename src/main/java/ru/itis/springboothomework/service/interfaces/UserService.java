package ru.itis.springboothomework.service.interfaces;


import javassist.NotFoundException;
import ru.itis.springboothomework.dto.UserDto;
import ru.itis.springboothomework.models.User;
import ru.itis.springboothomework.dto.UserFullDto;

import java.util.List;

public interface UserService {

    Boolean addUser(UserDto user);
    List<User> getUsers();
    User getUser(long id) throws NotFoundException;
    User getUser(String login) throws NotFoundException;
    void deleteUser(long id);
    List<User> getAllUsers(Integer page, Integer size);
    Boolean updateUser(UserFullDto userDto) throws NotFoundException;
    User getCurrentUser();
}
