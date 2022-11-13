package com.pdsacw.algomrexapibackend.Dto;

import java.util.ArrayList;

public class NewUserAnswerDto {

    private ArrayList<UserAnswerShortestPath> answerList;

    public NewUserAnswerDto(ArrayList<UserAnswerShortestPath> answerList) {
        this.answerList = answerList;
    }

    public NewUserAnswerDto() {
    }

    public ArrayList<UserAnswerShortestPath> getAnswerList() {
        return answerList;
    }


    public void setAnswerList(ArrayList<UserAnswerShortestPath> answerList) {
        this.answerList = answerList;
    }


    @Override
    public String toString() {
        return "NewUserAnswerDto{" +
                "measureList=" + answerList +
                '}';
    }
}
