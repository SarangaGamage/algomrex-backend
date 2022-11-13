package com.pdsacw.algomrexapibackend.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class DistanceTable {

    String rowHeader;
    String header;
    int weight;

    public DistanceTable(String rowHeader, String header, int weight){
        this.rowHeader = rowHeader;
        this.header = header;
        this.weight = weight;
    }

    public DistanceTable(){

    }



}
