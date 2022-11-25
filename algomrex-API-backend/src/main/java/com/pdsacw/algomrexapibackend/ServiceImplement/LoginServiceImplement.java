package com.pdsacw.algomrexapibackend.ServiceImplement;

import com.pdsacw.algomrexapibackend.Dto.User;
import com.pdsacw.algomrexapibackend.Entity.UserEntity;
import com.pdsacw.algomrexapibackend.Repository.UserRepository;
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
    UserRepository userRepository;
    @Override
    public ResponseEntity<Object> userLogin(User user) {

        UserEntity userEntity = userRepository.userLogin(user.getUsername(), user.getPassword());

        if(userEntity != null){
            return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, userEntity, Constant.SUCCESS);
        }else {
            return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, null, Constant.FAILURE);
        }
    }


    @Override
    public ResponseEntity<Object> userRegistration(User user) {
        try{
            if(userRepository.getUsername(user.getUsername()) != null){
                return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, "Username Already Taken!!");
            }
            UserEntity userEntity = new UserEntity();
            userEntity.setFirstname(user.getFirstName());
            userEntity.setLastname(user.getLastName());
            userEntity.setUsername(user.getUsername());
            userEntity.setPassword(user.getPassword());
            userRepository.save(userEntity);
            return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, "User Has Been Registered Successfully!!!");
        }catch(Exception ex){
            return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, "Something Went Wrong Please Try Again!!!");
        }
    }
}
