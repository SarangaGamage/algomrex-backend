package com.pdsacw.algomrexapibackend.Controller;


import com.pdsacw.algomrexapibackend.Dto.CommonUserAnswer;
import com.pdsacw.algomrexapibackend.Service.KnapsackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(value = "/Algomrex")
@CrossOrigin(origins = "*")
public class Knapsack {

    @Autowired
    KnapsackService knapsackService;

    @PostMapping(value = "/userAnswers2")
    HttpEntity<Object> checkUserAnswer(@RequestBody CommonUserAnswer commonUserAnswer, @RequestHeader int userId) throws UnsupportedEncodingException {
        return knapsackService.checkUserAnswer(commonUserAnswer, userId);
    }

    @GetMapping(value = "/getTableData2")
    HttpEntity<Object> getTableData() throws UnsupportedEncodingException {
        return knapsackService.getTableData();
    }

}
