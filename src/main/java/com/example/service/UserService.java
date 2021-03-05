package com.example.service;

import com.example.dao.RoleRepository;
import com.example.dao.UserRepository;
import com.example.dao.UserRoleRepository;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.entity.UserRole;
import com.example.model.UserAuthority;
import com.example.model.dto.UserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserService {

    @Inject
    UserRepository userRepository;
    @Inject
    RoleRepository roleRepository;
    @Inject
    UserRoleRepository userRoleRepository;

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

    public User register(UserDTO userDTO) {
        User userSaved = null;
        Role role = null;

        User user=userDTO.toUser();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userSaved = userRepository.save(user);

        for (UserAuthority roleItem : userDTO.getAuthority()) {
            role = roleRepository.findRoleByAuthority(roleItem);
            UserRole userRole = new UserRole(userSaved, role);
            userRoleRepository.save(userRole);
        }
        return userSaved;
    }

}
