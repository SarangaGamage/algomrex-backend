package com.pdsacw.algomrexapibackend.Service;

import com.pdsacw.algomrexapibackend.Dto.CommomUserAnswer;
import com.pdsacw.algomrexapibackend.Dto.NewUserAnswerDto;
import com.pdsacw.algomrexapibackend.Dto.UserAnswer;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface ShortestPathService {

    ResponseEntity<Object> checkUserAnswer(CommomUserAnswer answerList) throws UnsupportedEncodingException;
    ResponseEntity<Object> getTableData() throws UnsupportedEncodingException;
}
