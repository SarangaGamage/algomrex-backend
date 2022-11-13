package com.pdsacw.algomrexapibackend.Dto;

import java.util.ArrayList;

public class NewMeasurePackageDto {

    private ArrayList<UserAnswerShortestPath> answerList;

    public NewMeasurePackageDto(ArrayList<UserAnswerShortestPath> answerList) {
        this.answerList = answerList;
    }

    public NewMeasurePackageDto() {
    }

    public ArrayList<UserAnswerShortestPath> getAnswerList() {
        return answerList;
    }


    public void setAnswerList(ArrayList<UserAnswerShortestPath> answerList) {
        this.answerList = answerList;
    }


    @Override
    public String toString() {
        return "NewMeasurePackageDto{" +
                "measureList=" + answerList +
                '}';
    }
}
