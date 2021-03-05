package com.example.entity;

import com.example.model.UserAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "ROLE")
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AUTORITY")
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserAuthority authority;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserAuthority getAuthority() {
        return authority;
    }

    public void setAuthority(UserAuthority authority) {
        this.authority = authority;
    }
}
