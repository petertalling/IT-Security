package com.example.itsecurity.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue
    protected Long id;
    protected String username;
    protected String password;

    public Users(String name, String password) {
        this.username = name;
        this.password = password;
    }

    public Users(Long id, String name, String password) {
        this.id = id;
        this.username = name;
        this.password = password;
    }
}