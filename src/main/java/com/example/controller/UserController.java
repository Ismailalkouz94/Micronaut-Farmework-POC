package com.example.controller;

import com.example.entity.Airport;
import com.example.entity.User;
import com.example.service.AirportService;
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
@Secured(SecurityRule.IS_AUTHENTICATED)
public class UserController {
    @Inject
    UserService userService;

    @Post("/")
    @Secured("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    public User saveOrUpdate(@Body User user) {
        return userService.saveOrUpdateUser(user);

    }
}
