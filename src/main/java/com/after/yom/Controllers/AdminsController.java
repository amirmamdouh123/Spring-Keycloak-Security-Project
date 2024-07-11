package com.after.yom.Controllers;

import com.after.yom.realm.dto.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
@SecurityRequirement(name = "MyKeycloak")
public class AdminsController {

    @GetMapping
    public String admin(){
        return "admin only";
    }

    @GetMapping("user")
    public User user(){
        return User.builder()
                .firstName("amir")
                .lastName("mamdouh")
                .email("amir@gmail.com").build();
    }
}
