package com.pdsacw.algomrexapibackend.ServiceImplement;

import com.pdsacw.algomrexapibackend.Dto.*;

import com.pdsacw.algomrexapibackend.Service.ShortestPathService;

import com.pdsacw.algomrexapibackend.Utill.Constant;
import com.pdsacw.algomrexapibackend.Utill.responseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShortestPathServiceImplement<T> implements ShortestPathService {


    @Override
    public ResponseEntity<Object> checkUserAnswer(CommonUserAnswer answerList) {

        Node<String> startingNode = null;
        List<Node<String>> nodes = new ArrayList<>();
        Node<String> cityA = new Node<>("CityA");
        nodes.add(cityA);
        Node<String> cityB = new Node<>("CityB");
        nodes.add(cityB);
        Node<String> cityC = new Node<>("CityC");
        nodes.add(cityC);
        Node<String> cityD = new Node<>("CityD");
        nodes.add(cityD);
        Node<String> cityE = new Node<>("CityE");
        nodes.add(cityE);
        Node<String> cityF = new Node<>("CityF");
        nodes.add(cityF);
        Node<String> cityG = new Node<>("CityG");
        nodes.add(cityG);
        Node<String> cityH = new Node<>("CityH");
        nodes.add(cityH);
        Node<String> cityI = new Node<>("CityI");
        nodes.add(cityI);
        Node<String> cityJ = new Node<>("CityJ");
        nodes.add(cityJ);

        for(DistanceTable tableData : GlobalVariables.getInstance(false).createdTable){
            Node<String> startNode = null;
            Node<String> endNode = null;
            for(Node<String> node : nodes){
                if(node.getName().equals(answerList.getAnswerList().get(0).getFrom().replaceAll("\\s+", "")) && startNode == null){
                    startingNode = node;
                }
                if(node.getName().equals(tableData.getHeader().replaceAll("\\s+", ""))){
                    startNode = node;
                }
                if(node.getName().equals(tableData.getRowHeader().replaceAll("\\s+", ""))){
                    endNode = node;
                }
            }
            if(startNode != null){
                startNode.addAdjacentNode(endNode,tableData.getWeight(),startNode);
            }
        }
        Dijkstra<String> dijkstra = new Dijkstra<>();
        System.out.println(startingNode);
        if(startingNode != null){
            dijkstra.calculateShortestPath(startingNode);
        }
        ArrayList<Path> pathList = dijkstra.printPaths(nodes);

        int index=0;
        List<AnswerResult> checkedResults = new ArrayList<>();
        for(UserAnswer givenUserAnswer : answerList.getAnswerList()){
            if(Objects.equals(pathList.get(index).getPath().replaceAll("City",""), givenUserAnswer.getPath())){
                if(Objects.equals(pathList.get(index).getDistance(), givenUserAnswer.getDistance())){
                  checkedResults.add(new AnswerResult(givenUserAnswer.getLabelId(), 1,"Both distance and path are correct!!"));
                }else{
                    checkedResults.add(new AnswerResult(givenUserAnswer.getLabelId(),2,"Only path is correct distance is wrong!!"));
                }
            }else {
                if(Objects.equals(pathList.get(index).getDistance(), givenUserAnswer.getDistance())) {
                    checkedResults.add(new AnswerResult(givenUserAnswer.getLabelId(),2,"Only distance is correct and path is wrong!!"));
                }else{
                    checkedResults.add(new AnswerResult(givenUserAnswer.getLabelId(),0,"Both distance and path are wrong!!"));
                }
            }
            index++;
        }
        return responseHandler.generateResponse(HttpStatus.MULTI_STATUS,  checkedResults,  Constant.SUCCESS);
    }

    public int getRandomDistance() {
        Random random = new Random();
        return random.nextInt(9) + 1;
    }

    @Override
    public ResponseEntity<Object> getTableData() {
        String[] headers = new String[]{"City A", "City B", "City C", "City D","City E","City F","City G","City H","City I"};
        String[] rowHeaders = new String[]{"City A", "City B", "City C", "City D","City E","City F","City G","City H","City I","City J"};

        GlobalVariables distanceTable = GlobalVariables.getInstance(true);

        ArrayList<String> usedHeaders = new ArrayList<>();
        for (String rowHeader : headers) {
            for (String header : rowHeaders) {
                if (!rowHeader.equals(header) && !usedHeaders.contains(header)) {
                    distanceTable.createdTable.add(new DistanceTable(rowHeader,header,getRandomDistance()));
                }
            }
            usedHeaders.add(rowHeader);
        }
        return responseHandler.generateResponse(HttpStatus.MULTI_STATUS, distanceTable.createdTable, "ssss");
    }





    public ResponseEntity<Object> getShortestPathToAllVertex(String source, String userAnswer){

        Node<String> sourceNode = null;
        List<Node<String>> nodes1 = new ArrayList<>();
        Node<String> cityA = new Node<>("CityA");
        nodes1.add(cityA);
        Node<String> cityB = new Node<>("CityB");
        nodes1.add(cityB);
        Node<String> cityC = new Node<>("CityC");
        nodes1.add(cityC);
        Node<String> cityD = new Node<>("CityD");
        nodes1.add(cityD);
        Node<String> cityE = new Node<>("CityE");
        nodes1.add(cityE);
        Node<String> cityF = new Node<>("CityF");
        nodes1.add(cityF);
        Node<String> cityG = new Node<>("CityG");
        nodes1.add(cityG);
        Node<String> cityH = new Node<>("CityH");
        nodes1.add(cityH);
        Node<String> cityI = new Node<>("CityI");
        nodes1.add(cityI);
        Node<String> cityJ = new Node<>("CityJ");
        nodes1.add(cityJ);


        for(Node<String> node : nodes1){
            if(node.getName() == source){
                sourceNode = node;
                break;
            }
        }

//        while(sourceNode.getDistance() != null )


        return responseHandler.generateResponse(HttpStatus.MULTI_STATUS, null, "ssss");
    }
}
    

