package com.pdsacw.algomrexapibackend.Service;

import com.pdsacw.algomrexapibackend.Dto.userDto;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;

public interface shortestPathService {

    ResponseEntity<Object> calculatePath() throws UnsupportedEncodingException;
}
