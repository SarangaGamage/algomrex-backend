package com.pdsacw.algomrexapibackend.Dto;

import java.util.ArrayList;

public class GlobalVariables {

    public ArrayList<DistanceTable> createdTable;

    private GlobalVariables() {
        createdTable = new ArrayList<DistanceTable>();
    }

    private static GlobalVariables instance;

    public static GlobalVariables getGlobalVariable(Boolean reset) {
        if(reset){
            instance = null;
        }
        if (instance == null ) {
            instance = new GlobalVariables();
        }
        return instance;
    }
}
