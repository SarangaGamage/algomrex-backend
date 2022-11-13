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
    public ResponseEntity<Object> checkUserAnswer(CommomUserAnswer answerList) {


    //    String startPoint = newUserAnswerDto.getAnswerList().get(0).getFrom();
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

        for(DistanceTable tableData : GlobalVariables.getInstance(false).createdTable){
            Node<String> startNode = null;
            Node<String> endNode = null;
            for(Node<String> node : nodes){
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
        System.out.println(nodes.get(3).getName());
        dijkstra.calculateShortestPath(nodes.get(2));

        ArrayList<Path> pathList = dijkstra.printPaths(nodes);

        int index=0;
        for(UserAnswer userAnswer : answerList.getAnswerList()){

            if(Objects.equals(pathList.get(index).getPath().replaceAll("City",""), userAnswer.getPath())){
                System.out.println(index+"is correct");
            }else {
                System.out.println(index+"is wrong");
            }
            index++;
        }
        return responseHandler.generateResponse(HttpStatus.MULTI_STATUS,  null,  Constant.SUCCESS);
    }

    public int getRandomDistance() {
        Random random = new Random();
        return random.nextInt(9) + 1;
    }


    @Override
    public ResponseEntity<Object> getTableData() {
        String[] headers = new String[]{"City A", "City B", "City C", "City D"};
        String[] rowHeaders = new String[]{"City A", "City B", "City C", "City D", "City E"};

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
}
    

