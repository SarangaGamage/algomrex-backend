package com.pdsacw.algomrexapibackend.ServiceImplement;

import com.pdsacw.algomrexapibackend.Dto.User;
import com.pdsacw.algomrexapibackend.Entity.UserEntity;
import com.pdsacw.algomrexapibackend.Repository.LoginRepository;
import com.pdsacw.algomrexapibackend.Service.LoginService;
import com.pdsacw.algomrexapibackend.Utill.Constant;
import com.pdsacw.algomrexapibackend.Utill.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImplement implements LoginService {

    @Autowired
    LoginRepository loginRepository;
    @Override
    public ResponseEntity<Object> userLogin(User user) {

        UserEntity userEntity = loginRepository.userLogin(user.getUsername(), user.getPassword());

        if(userEntity != null){
            return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, userEntity, Constant.SUCCESS);
        }else {
            return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, null, Constant.FAILURE);
        }
    }
}
