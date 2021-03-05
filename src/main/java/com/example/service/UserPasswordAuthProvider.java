package com.example.service;

import com.example.dao.UserRepository;
import com.example.dao.UserRoleRepository;
import com.example.entity.Role;
import com.example.entity.User;
import io.micronaut.security.authentication.*;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class UserPasswordAuthProvider implements AuthenticationProvider {
    @Inject
    private UserRepository userRepository;
    @Inject
    UserRoleRepository userRoleRepository;

    @Inject //you can use @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest req) {

        String username = req.getIdentity().toString();
        String password = req.getSecret().toString();
        User user = userRepository.findUserByUserName(username);
        List<Role> roles = userRoleRepository.findRoleByUser(user);

        Optional<AuthenticationFailed> authenticationFailed = validate(user, password);
        if (authenticationFailed.isPresent()) {
            return Flowable.just(authenticationFailed.get());
        }
        UserDetails details = new UserDetails(username, roles.stream().map(x -> x.getAuthority().toString()).collect(Collectors.toList()));
        return Flowable.just(details);

    }

    private Optional<AuthenticationFailed> validate(User user, String rowPassword) {

        AuthenticationFailed authenticationFailed = null;
        if (user == null) {
            authenticationFailed = new AuthenticationFailed(AuthenticationFailureReason.USER_NOT_FOUND);

        } else if (!user.isEnabled()) {
            authenticationFailed = new AuthenticationFailed(AuthenticationFailureReason.USER_DISABLED);

        } else if (user.isAccountExpired()) {
            authenticationFailed = new AuthenticationFailed(AuthenticationFailureReason.ACCOUNT_EXPIRED);

        } else if (user.isAccountLocked()) {
            authenticationFailed = new AuthenticationFailed(AuthenticationFailureReason.ACCOUNT_LOCKED);

        } else if (user.isPasswordExpired()) {
            authenticationFailed = new AuthenticationFailed(AuthenticationFailureReason.PASSWORD_EXPIRED);

        } else if (!bCryptPasswordEncoder.matches(rowPassword, user.getPassword())) {
            authenticationFailed = new AuthenticationFailed(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH);
        }
        return Optional.ofNullable(authenticationFailed);
    }
}
