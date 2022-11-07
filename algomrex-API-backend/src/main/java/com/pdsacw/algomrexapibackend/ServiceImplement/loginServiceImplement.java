package com.pdsacw.algomrexapibackend.ServiceImplement;

import com.pdsacw.algomrexapibackend.Dto.userDto;
import com.pdsacw.algomrexapibackend.Service.loginService;
import com.pdsacw.algomrexapibackend.Utill.Constant;
import com.pdsacw.algomrexapibackend.Utill.responseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class loginServiceImplement implements loginService {

    @Override
    public ResponseEntity<Object> userLogin(userDto userDto) {
        return responseHandler.generateResponse(HttpStatus.MULTI_STATUS, null, Constant.FAILURE);
    }
}
