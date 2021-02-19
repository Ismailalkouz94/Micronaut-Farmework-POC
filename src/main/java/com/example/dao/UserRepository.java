package com.example.dao;

import com.example.entity.Airport;
import com.example.entity.User;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
 
 @Executable
 User findUserByUserNameAndPassword(String userName,String password);
 
}