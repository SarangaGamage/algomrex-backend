package com.pdsacw.algomrexapibackend.ServiceImplement;

import com.pdsacw.algomrexapibackend.Dto.Dijkstra;
import com.pdsacw.algomrexapibackend.Dto.Node;

import com.pdsacw.algomrexapibackend.Service.shortestPathService;

import com.pdsacw.algomrexapibackend.Utill.Constant;
import com.pdsacw.algomrexapibackend.Utill.responseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.KeyStore;
import java.util.*;

@Service
public class shortestPathServiceImplement implements shortestPathService {


    @Override
    public ResponseEntity<Object> calculatePath() {

        Node<String> nodeA = new Node<>("A");
        Node<String> nodeB = new Node<>("B");
        Node<String> nodeC = new Node<>("C");
        Node<String> nodeD = new Node<>("D");
        Node<String> nodeE = new Node<>("E");
        // Node<String> nodeF = new Node<>("F");

        nodeA.addAdjacentNode(nodeE, 29);
        nodeA.addAdjacentNode(nodeD, 6);
        nodeA.addAdjacentNode(nodeC, 38);

        nodeB.addAdjacentNode(nodeC, 84);
        nodeB.addAdjacentNode(nodeD, 13);
        nodeB.addAdjacentNode(nodeE, 15);

        nodeC.addAdjacentNode(nodeD, 26);
        nodeC.addAdjacentNode(nodeE, 26);
        nodeC.addAdjacentNode(nodeB, 84);

        nodeD.addAdjacentNode(nodeE, 22);
        nodeD.addAdjacentNode(nodeB, 13);

        // nodeE.addAdjacentNode(nodeF, 2);

        Dijkstra<String> dijkstra = new Dijkstra<>();
        dijkstra.calculateShortestPath(nodeA);


        return responseHandler.generateResponse(HttpStatus.MULTI_STATUS, dijkstra.printPaths(List.of(nodeA, nodeB, nodeC, nodeD, nodeE)), Constant.FAILURE);
    }
    
}
