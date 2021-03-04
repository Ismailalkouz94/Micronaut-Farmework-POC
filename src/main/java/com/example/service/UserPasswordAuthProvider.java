package com.example.service;

import com.example.dao.UserRepository;
import com.example.entity.User;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Primary;
import io.micronaut.security.authentication.*;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Collections;

@Singleton
public class UserPasswordAuthProvider implements AuthenticationProvider {
    @Inject
    private UserRepository userRepository;


    @Inject //you can use @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest req) {
        String username = req.getIdentity().toString();
        String password = req.getSecret().toString();
        User user = userRepository.findUserByUserName(username);
        if (user !=null){
            if (bCryptPasswordEncoder.matches(password,user.getPassword())) {
                UserDetails details = new UserDetails(username, Collections.singletonList(user.getRole().toString()));
                return Flowable.just(details);
            }else {
                return Flowable.just(new AuthenticationFailed(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH));
            }
        } else{
            return Flowable.just(new AuthenticationFailed(AuthenticationFailureReason.USER_NOT_FOUND));
        }
    }
}
