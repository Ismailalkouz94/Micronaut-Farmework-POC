package com.example.service;

import com.example.dao.UserRepository;
import com.example.entity.User;
import io.micronaut.security.authentication.*;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;

@Singleton
public class UserPasswordAuthProvider implements AuthenticationProvider {
    @Inject
    private UserRepository userRepository;

    @Override
    public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest req) {
        String username = req.getIdentity().toString();
        String password = req.getSecret().toString();
        User user = userRepository.findUserByUserNameAndPassword(username,password);
        if (user !=null){
            UserDetails details = new UserDetails(username, Collections.singletonList(user.getRole().toString()));
            return Flowable.just(details);
        } else{
            return Flowable.just(new AuthenticationFailed());
        }
    }
}
