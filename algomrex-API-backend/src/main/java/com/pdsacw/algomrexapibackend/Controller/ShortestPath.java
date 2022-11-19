package com.pdsacw.algomrexapibackend.Controller;


import com.pdsacw.algomrexapibackend.Dto.CommonUserAnswer;
import com.pdsacw.algomrexapibackend.Service.ShortestPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(value = "/Algomrex")
@CrossOrigin(origins = "*")
public class ShortestPath {

    @Autowired
    ShortestPathService shortPath;

    @PostMapping(value = "/userAnswers")
    HttpEntity<Object> checkUserAnswer(@RequestBody CommonUserAnswer commonUserAnswer) throws UnsupportedEncodingException {
        return shortPath.checkUserAnswer(commonUserAnswer);
    }

    @GetMapping(value = "/getTableData")
    HttpEntity<Object> getTableData() throws UnsupportedEncodingException {
        return shortPath.getTableData();
    }
}


