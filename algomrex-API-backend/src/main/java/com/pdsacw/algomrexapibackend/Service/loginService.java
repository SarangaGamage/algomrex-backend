package com.pdsacw.algomrexapibackend.Service;

import com.pdsacw.algomrexapibackend.Dto.userDto;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;

public interface loginService {
    ResponseEntity<Object> userLogin(userDto userDto) throws UnsupportedEncodingException;
}
