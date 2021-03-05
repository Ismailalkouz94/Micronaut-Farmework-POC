package com.example.dao;

import com.example.entity.Role;
import com.example.model.UserAuthority;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

 @Executable
 Role findRoleByAuthority(UserAuthority authority);

}