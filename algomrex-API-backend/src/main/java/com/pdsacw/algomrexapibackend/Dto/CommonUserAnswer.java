package com.pdsacw.algomrexapibackend.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommonUserAnswer {
    private String questionId;
    private List<ShortestPathUserAnswer> answerList;
    private MinimumConnectorsUserAnswer minimumConnectorsUserAnswer;
    private KnapsackUserAnswer knapsackUserAnswer;
}
