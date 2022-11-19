package com.pdsacw.algomrexapibackend.ServiceImplement;

import com.pdsacw.algomrexapibackend.Dto.User;
import com.pdsacw.algomrexapibackend.Entity.UserEntity;
import com.pdsacw.algomrexapibackend.Repository.Login;
import com.pdsacw.algomrexapibackend.Service.LoginService;
import com.pdsacw.algomrexapibackend.Utill.Constant;
import com.pdsacw.algomrexapibackend.Utill.responseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImplement implements LoginService {

    @Autowired
    Login loginRepository;
    @Override
    public ResponseEntity<Object> userLogin(User user) {

        UserEntity userEntity = loginRepository.userLogin(user.getUsername(), user.getPassword());

        if(userEntity != null){
            return responseHandler.generateResponse(HttpStatus.MULTI_STATUS, userEntity, Constant.SUCCESS);
        }else {
            return responseHandler.generateResponse(HttpStatus.MULTI_STATUS, null, Constant.FAILURE);
        }
    }
}
