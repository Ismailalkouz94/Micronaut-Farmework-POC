package com.example.service;

import com.example.dao.AirportRepository;
import com.example.dao.UserRepository;
import com.example.entity.Airport;
import com.example.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserService {

    @Inject
    UserRepository userRepository;

    @Inject //you can use @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    public User saveOrUpdateUser(User user) {
        User userSaved = null;
        if (user.getId() != null && userRepository.existsById(user.getId())) {
            userSaved = userRepository.update(user);
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userSaved = userRepository.save(user);
        }
        return userSaved;
    }
}
