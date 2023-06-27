package com.medivh.electroniccommerce.request;

import lombok.Data;

@Data
public class RegisterRequest {

    private String username;
    private String telephone;
    private String password;
    private String password_again;
}
