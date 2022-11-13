package com.pdsacw.algomrexapibackend.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class UserAnswerShortestPath {

    private String distance;
    private String path;
    private String from;
    private String to;
    private boolean status;


    public UserAnswerShortestPath(){

    }

    private UserAnswerShortestPath(Builder builder){
        distance = builder.distance;
        path = builder.path;
        from = builder.from;
        to = builder.to;
        status = builder.status;
    }

    public static final class Builder{
        private String distance;
        private String path;
        private String from;
        private String to;
        private boolean status;

        public Builder(){

        }

        public UserAnswerShortestPath build() {
            return new UserAnswerShortestPath(this);
        }
    }
}
