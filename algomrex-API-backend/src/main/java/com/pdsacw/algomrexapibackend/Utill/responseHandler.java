package com.pdsacw.algomrexapibackend.Utill;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class responseHandler {
    public static ResponseEntity<Object> generateResponse(HttpStatus status, Object responseObj,Object responseObj2, String message) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Status", status.value());
        map.put("Table", responseObj);
        map.put("Data", responseObj2);
        map.put("Message", message);
        return new ResponseEntity<Object>(map, status);
    }

    public static ResponseEntity<Object> generateResponse(HttpStatus status, Object responseObj, String message) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Status", status.value());

        map.put("Data", responseObj);
        map.put("Message", message);
        return new ResponseEntity<Object>(map, status);
    }

    public static ResponseEntity<Object> generateResponse(HttpStatus status,  String message) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Status", status.value());
        map.put("Message", message);
        return new ResponseEntity<Object>(map, status);
    }
}
