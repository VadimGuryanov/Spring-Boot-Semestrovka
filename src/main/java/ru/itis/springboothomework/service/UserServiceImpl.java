package ru.itis.springboothomework.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.springboothomework.aspects.ExceptionAnnotation;
import ru.itis.springboothomework.dto.UserDto;
import ru.itis.springboothomework.models.User;
import ru.itis.springboothomework.dto.UserFullDto;
import ru.itis.springboothomework.repository.UserRepository;
import ru.itis.springboothomework.service.interfaces.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

//    @Autowired
//    private EntityManagerRepository<User> repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Override
//    public void addUser(UserDto user) {
//        User u = new User();
//        u.setEmail(user.getEmail());
//        u.setHashPassword(passwordEncoder.encode(user.getPassword()));
//        System.out.println(u.getHashPassword());
//        u.setBirthday(user.getBirthday());
//        u.setCountry(user.getCountry());
//        u.setGender(user.getGender());
//        u.setRole(Role.USER);
//        repository.save(u);
//    }

    @Override
    @ExceptionAnnotation
    public Boolean addUser(UserDto user) {
        if (repository.findUserByLogin(user.getLogin()).isPresent()) return false;
        User u = new User();
        u.setLogin(user.getLogin());
        u.setHashPassword(passwordEncoder.encode(user.getPassword()));
        u.setAge(user.getAge());
        u.setCountry(user.getCountry());
        u.setGender(user.getGender());
        u.setName(user.getName());
        u.setLastName(user.getLastName());
        u.setSity(user.getSity());
        repository.save(u);
        return true;
    }

    @Override
    @ExceptionAnnotation
    public List<User> getUsers() {
        return repository.findAll();
    }

    @Override
    @ExceptionAnnotation
    public User getUser(long id) throws NotFoundException {
        Optional<User> optional = repository.findById(id);
        if (optional.isPresent()) return optional.get();
        throw new NotFoundException("Not found");

    }

    @Override
    @ExceptionAnnotation
    public User getUser(String login) throws NotFoundException {
        Optional<User> optional = repository.findUserByLogin(login);
        if (optional.isPresent()) return optional.get();
        throw new NotFoundException("Not found");
    }

    @Override
    @ExceptionAnnotation
    public void deleteUser(long id) {
        repository.deleteById(id);
    }

    @Override
    @ExceptionAnnotation
    public List<User> getAllUsers(Integer page, Integer size) {
        PageRequest request = PageRequest.of(page, size);
        Page<User> pageResult = repository.findAll(request);
        return pageResult.getContent();
    }

    @Override
    @ExceptionAnnotation
    public Boolean updateUser(UserFullDto userDto) throws NotFoundException {
        User u = getCurrentUser();
        if (userDto.getAge() != null) {
            u.setAge(userDto.getAge());
        }
        if (userDto.getCountry() != null) {
            u.setCountry(userDto.getCountry());
        }
        if (userDto.getGender() != null) {
            u.setGender(userDto.getGender());
        }
        if (userDto.getName() != null) {
            u.setName(userDto.getName());
        }
        if (userDto.getLastName() != null) {
            u.setLastName(userDto.getLastName());
        }
        if (userDto.getSity() != null) {
            u.setSity(userDto.getSity());
        }
        if (userDto.getMobile() != null) {
            u.setMobile(userDto.getMobile());
        }
        if (userDto.getSocial() != null) {
            u.setSocial(userDto.getSocial());
        }
        if (userDto.getDescription() != null) {
            u.setDescription(userDto.getDescription());
        }
        if (userDto.getPhotoUrl() != null) {
            u.setPhotoUrl(userDto.getPhotoUrl());
        }
        if (userDto.getStudy() != null) {
            u.setStudy(userDto.getStudy());
        }
        if (userDto.getWork() != null) {
            u.setWork(userDto.getWork());
        }
//        repository.delete(u);
        repository.save(u);
//        repository.updateUser(u.getId(), u.getName(), u.getLastName(), u.getCountry(),
//                             u.getSity(), u.getGender(), u.getAge(), u.getStudy(), u.getWork(),
//                             u.getMobile(), u.getSocial(), u.getPhotoUrl(), u.getDescription());
        return true;
    }

    @ExceptionAnnotation
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (repository.findUserByLogin(auth.getName()).isPresent()) {
            return repository.findUserByLogin(auth.getName()).get();
        }
        return null;
    }


}
