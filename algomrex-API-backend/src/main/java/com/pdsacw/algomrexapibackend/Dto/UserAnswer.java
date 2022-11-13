package com.pdsacw.algomrexapibackend.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAnswer {

    private String distance;
    private String path;
    private String from;
    private String to;
    private boolean status;
}
