package com.pdsacw.algomrexapibackend.Utill;

import com.pdsacw.algomrexapibackend.Entity.WinnerEntity;
import com.pdsacw.algomrexapibackend.Repository.WinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Common {


    public static WinnerEntity saveWinnerUserDetails(int userId, String gameId, int gameType){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));

       // WinnerRepository winnerRepository = null;

        WinnerEntity winnerEntity = new WinnerEntity();
        winnerEntity.setUserId(userId);
        winnerEntity.setGameId(gameId);
        winnerEntity.setGameType(gameType);
        winnerEntity.setDateTime(dtf.format(now));

       return winnerEntity;

    }


    public static String generateGameId(String gameCode){
        Random random = new Random();
        return gameCode+"-"+random.nextInt(900) + 100;
    }
}
