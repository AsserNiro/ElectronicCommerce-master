package com.medivh.electroniccommerce.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private int id;
    private String username;
    private String password;
    private String telephone;
    private String address;
    private String email;

    public Account(String username, String password, String telephone, String address, String email) {
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.address = address;
        this.email = email;
    }

    public Account(String username, String password, String telephone) {
        this.username = username;
        this.password = password;
        this.telephone = telephone;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
