package com.pdsacw.algomrexapibackend.Controller;


import com.pdsacw.algomrexapibackend.Dto.userDto;
import com.pdsacw.algomrexapibackend.Service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(value = "/Algomrex")
@CrossOrigin(origins = "*")
public class loginController {

    @Autowired
    loginService login;

    @PostMapping(value = "/userLogin")
    HttpEntity<Object> userLogin(@RequestBody userDto user) throws UnsupportedEncodingException {
        return login.userLogin(user);
    }
}