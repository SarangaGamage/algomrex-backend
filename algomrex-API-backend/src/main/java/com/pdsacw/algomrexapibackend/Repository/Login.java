package com.pdsacw.algomrexapibackend.Repository;


import com.pdsacw.algomrexapibackend.Entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Login {
    @Query("SELECT u FROM UserEntity u WHERE u.username=:username AND u.password=:password")
    UserEntity userLogin(String username, String password);

}
