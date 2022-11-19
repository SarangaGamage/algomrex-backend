package com.pdsacw.algomrexapibackend.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnswerResult {
    private String labelId;

    private int status;
    private String result;


    public AnswerResult(String labelId, int status, String result) {
    this.labelId = labelId;
        this.status = status;
        this.result = result;
    }

    public AnswerResult() {

    }
}
