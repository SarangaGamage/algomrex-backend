package com.pdsacw.algomrexapibackend.Service;

import com.pdsacw.algomrexapibackend.Dto.CommonUserAnswer;
import com.pdsacw.algomrexapibackend.Dto.KnapsackUserAnswer;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;

public interface KnapsackService {

    ResponseEntity<Object> getTableData() throws UnsupportedEncodingException;
    ResponseEntity<Object> checkUserAnswer(CommonUserAnswer commonUserAnswer, int userId) throws UnsupportedEncodingException;
}
