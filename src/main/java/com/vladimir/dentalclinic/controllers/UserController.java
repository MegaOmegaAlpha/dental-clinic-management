package com.vladimir.dentalclinic.controllers;

import com.vladimir.dentalclinic.dto.AuthenticationStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public AuthenticationStatus user() {
        return new AuthenticationStatus("Success");
    }

}
