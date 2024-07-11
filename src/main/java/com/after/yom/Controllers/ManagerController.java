package com.after.yom.Controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/manager")
@SecurityRequirement(name = "MyKeycloak")
public class ManagerController {

    @RequestMapping(path = "/get",method = RequestMethod.GET)
//    @PreAuthorize("hasRole('manager')")
    public String manager(){
        return "manager only";
    }
}
