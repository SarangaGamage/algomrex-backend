package com.pdsacw.algomrexapibackend.Controller;


import com.pdsacw.algomrexapibackend.Dto.CommomUserAnswer;
import com.pdsacw.algomrexapibackend.Dto.NewUserAnswerDto;
import com.pdsacw.algomrexapibackend.Dto.UserAnswer;
import com.pdsacw.algomrexapibackend.Service.ShortestPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping(value = "/Algomrex")
@CrossOrigin(origins = "*")
public class shortestPath {

    @Autowired
    ShortestPathService shortPath;

    @PostMapping(value = "/userAnswers")
    HttpEntity<Object> checkUserAnswer(@RequestBody CommomUserAnswer answerList) throws UnsupportedEncodingException {
        return shortPath.checkUserAnswer(answerList);
    }

    @GetMapping(value = "/getTableData")
    HttpEntity<Object> getTableData() throws UnsupportedEncodingException {
        return shortPath.getTableData();
    }

}
