package com.pdsacw.algomrexapibackend.Service;

import com.pdsacw.algomrexapibackend.Dto.User;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;

public interface LoginService {
    ResponseEntity<Object> userLogin(User user) throws UnsupportedEncodingException;
    ResponseEntity<Object> userRegistration(User user) throws UnsupportedEncodingException;
}
