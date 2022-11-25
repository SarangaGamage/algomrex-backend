package com.pdsacw.algomrexapibackend.Repository;


import com.pdsacw.algomrexapibackend.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.username=:username AND u.password=:password")
    UserEntity userLogin(String username, String password);

    @Query("SELECT u FROM UserEntity u WHERE u.username=:username")
    UserEntity getUsername(String username);
}
