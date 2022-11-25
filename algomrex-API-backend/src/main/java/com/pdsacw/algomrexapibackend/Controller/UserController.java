package com.pdsacw.algomrexapibackend.Controller;


import com.pdsacw.algomrexapibackend.Dto.User;
import com.pdsacw.algomrexapibackend.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(value = "/Algomrex")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    LoginService loginService;

    @PostMapping(value = "/userLogin")
    HttpEntity<Object> userLogin(@RequestBody User user) throws UnsupportedEncodingException {
        return loginService.userLogin(user);
    }

    @PostMapping(value = "/userRegistration")
    HttpEntity<Object> userRegistration(@RequestBody User user) throws UnsupportedEncodingException {
        return loginService.userRegistration(user);
    }
}