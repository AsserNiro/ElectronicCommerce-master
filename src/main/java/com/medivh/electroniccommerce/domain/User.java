package com.medivh.electroniccommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String telephone;

    public User(String name,String password,String telephone){
        this.username = name;
        this.password = password;
        this.telephone = telephone;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
