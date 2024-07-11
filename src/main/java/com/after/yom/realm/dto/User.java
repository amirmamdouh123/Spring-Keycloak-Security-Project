package com.after.yom.realm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
