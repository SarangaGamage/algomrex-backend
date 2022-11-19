package com.pdsacw.algomrexapibackend.Service;

import com.pdsacw.algomrexapibackend.Dto.CommonUserAnswer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;

public interface ShortestPathService {

    ResponseEntity<Object> checkUserAnswer(CommonUserAnswer commonUserAnswer, int userId) throws UnsupportedEncodingException;
    ResponseEntity<Object> getTableData() throws UnsupportedEncodingException;
}
