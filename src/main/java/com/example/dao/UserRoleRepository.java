package com.example.dao;

import com.example.entity.Role;
import com.example.entity.User;
import com.example.entity.UserRole;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long>{

 @Executable
 List<Role> findRoleByUser(User user);

}