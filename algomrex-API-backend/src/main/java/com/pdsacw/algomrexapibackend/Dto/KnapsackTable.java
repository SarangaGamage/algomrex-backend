package com.pdsacw.algomrexapibackend.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KnapsackTable {

    String header;
    String rowHeader;
    int weight;

    public KnapsackTable(String rowHeader, String header, int weight) {
        this.header = header;
        this.rowHeader = rowHeader;
        this.weight = weight;
    }

    public KnapsackTable() {

    }
}
