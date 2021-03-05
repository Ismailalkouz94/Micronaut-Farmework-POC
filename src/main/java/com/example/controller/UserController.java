package com.example.controller;

import com.example.entity.User;
import com.example.model.dto.UserDTO;
import com.example.service.UserService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import javax.inject.Inject;

@Controller("/user")
@Secured(SecurityRule.IS_ANONYMOUS)
public class UserController {
    @Inject
    UserService userService;

    @Post("/")
    @Produces(MediaType.APPLICATION_JSON)
    public User register(@Body UserDTO user) {
        return userService.register(user);
    }
}
