package com.pdsacw.algomrexapibackend.ServiceImplement;

import com.pdsacw.algomrexapibackend.Dto.*;

import com.pdsacw.algomrexapibackend.Entity.ShortestDistanceBetweenCitiesEntity;
import com.pdsacw.algomrexapibackend.Repository.ShortestDistanceBetweenCities;
import com.pdsacw.algomrexapibackend.Service.ShortestPathService;

import com.pdsacw.algomrexapibackend.Utill.Common;
import com.pdsacw.algomrexapibackend.Utill.Constant;
import com.pdsacw.algomrexapibackend.Utill.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShortestPathServiceImplement implements ShortestPathService {

    @Autowired
    ShortestDistanceBetweenCities shortestDistanceBetweenCities;
    @Override
    public ResponseEntity<Object> checkUserAnswer(CommonUserAnswer commonUserAnswer, int userId) {

        try {
            Node startingNode = null;
            List<Node> nodes = new ArrayList<>();
            Node cityA = new Node("CityA");
            nodes.add(cityA);
            Node cityB = new Node("CityB");
            nodes.add(cityB);
            Node cityC = new Node("CityC");
            nodes.add(cityC);
            Node cityD = new Node("CityD");
            nodes.add(cityD);
            Node cityE = new Node("CityE");
            nodes.add(cityE);
            Node cityF = new Node("CityF");
            nodes.add(cityF);
            Node cityG = new Node("CityG");
            nodes.add(cityG);
            Node cityH = new Node("CityH");
            nodes.add(cityH);
            Node cityI = new Node("CityI");
            nodes.add(cityI);
            Node cityJ = new Node("CityJ");
            nodes.add(cityJ);

            for (DistanceTable tableData : GlobalVariables.getGlobalVariable(false).createdTable) {
                Node startNode = null;
                Node endNode = null;
                for (Node node : nodes) {
                    if (Objects.equals(commonUserAnswer.getQuestionId(), "1") && node.getName().equals(commonUserAnswer.getAnswerList().get(0).getFrom().replaceAll("\\s+", "")) && startNode == null) {
                        startingNode = node;
                    }
                    if (node.getName().equals(tableData.getHeader().replaceAll("\\s+", ""))) {
                        startNode = node;
                    }
                    if (node.getName().equals(tableData.getRowHeader().replaceAll("\\s+", ""))) {
                        endNode = node;
                    }
                }
                if (startNode != null) {
                    startNode.addAdjacentNode(endNode, tableData.getWeight(), startNode);
                }
            }

            if (Objects.equals(commonUserAnswer.getQuestionId(), "1")) {
                Dijkstra dijkstra = new Dijkstra();
                System.out.println(startingNode);
                if (startingNode != null) {
                    dijkstra.calculateShortestPath(startingNode);
                }
                ArrayList<Path> pathList = dijkstra.printPaths(nodes);

                int index = 0;
                boolean allAnswerCorrect = true;
                List<AnswerResult> checkedResults = new ArrayList<>();
                for (ShortestPathUserAnswer givenShortestPathUserAnswer : commonUserAnswer.getAnswerList()) {
                    if (Objects.equals(pathList.get(index).getPath().replaceAll("City", ""), givenShortestPathUserAnswer.getPath())) {
                        if (Objects.equals(pathList.get(index).getDistance(), givenShortestPathUserAnswer.getDistance())) {
                            checkedResults.add(new AnswerResult(givenShortestPathUserAnswer.getLabelId(), 1, "Both distance and path are correct!!"));
                        } else {
                            checkedResults.add(new AnswerResult(givenShortestPathUserAnswer.getLabelId(), 2, "Only path is correct distance is wrong!!"));
                            allAnswerCorrect = false;
                        }
                    } else {
                        if (Objects.equals(pathList.get(index).getDistance(), givenShortestPathUserAnswer.getDistance())) {
                            checkedResults.add(new AnswerResult(givenShortestPathUserAnswer.getLabelId(), 2, "Only distance is correct and path is wrong!!"));
                            allAnswerCorrect = false;
                        } else {
                            checkedResults.add(new AnswerResult(givenShortestPathUserAnswer.getLabelId(), 0, "Both distance and path are wrong!!"));
                            allAnswerCorrect = false;
                        }
                    }
                    index++;
                }

                if(allAnswerCorrect){
                    setDistanceBetweenCitiesForCorrectAnswers(GlobalVariables.getGlobalVariable(false).createdTable, userId);
                    Common.saveWinnerUserDetails(userId,"Short-Path",1);
                }

                return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, checkedResults, Constant.SUCCESS);

            } else if (Objects.equals(commonUserAnswer.getQuestionId(), "2")) {
                AnswerResult checkedResults;
                int totalDistanceOfMinimumConnectors = 0;

                StringBuilder pathOfMinimumConnectors = new StringBuilder();
                Stack<Node> settleNodes = new Stack<>();
                settleNodes.push(cityA);
                for (int i = 0; i < nodes.size() - 1; i++) {
                    Map.Entry<Node, Integer> min = settleNodes.peek().getAdjacentNodes()
                            .entrySet().stream().filter(entry -> !settleNodes.contains(entry.getKey()))
                            .min(Map.Entry.comparingByValue(Integer::compareTo))
                            .get();
                    System.out.println("Node is " + min.getKey().getName());
                    System.out.println("Distance is " + min.getValue());
                    System.out.println("--------------------------------");
                    settleNodes.add(min.getKey());
                    totalDistanceOfMinimumConnectors = totalDistanceOfMinimumConnectors + min.getValue();
                    pathOfMinimumConnectors.append(min.getKey().getName().replaceAll("City", ""));
                }
                if (totalDistanceOfMinimumConnectors == Integer.parseInt(commonUserAnswer.getMinimumConnectorsUserAnswer().getTotalDistance())) {
                    if (pathOfMinimumConnectors.toString().equals(commonUserAnswer.getMinimumConnectorsUserAnswer().getPath().replaceAll("[\\-\\+\\.\\^:,]", ""))) {
                        checkedResults = new AnswerResult("", 1, "Both distance and path are correct!!");
                        setDistanceBetweenCitiesForCorrectAnswers(GlobalVariables.getGlobalVariable(false).createdTable, userId);
                        Common.saveWinnerUserDetails(userId,"Mini-Con",2);
                    } else {
                        checkedResults = new AnswerResult("", 2, "Only distance is correct!!");
                    }
                } else {
                    if (pathOfMinimumConnectors.toString().equals(commonUserAnswer.getMinimumConnectorsUserAnswer().getPath().replaceAll("[\\-\\+\\.\\^:,]", ""))) {
                        checkedResults = new AnswerResult("", 2, "Only path is correct!!");
                    } else {
                        checkedResults = new AnswerResult("", 0, "Both distance and path are wrong!!");
                    }
                }
                return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, checkedResults, Constant.SUCCESS);
            }
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, null, Constant.SUCCESS);
        }
        return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, null, Constant.SUCCESS);
    }

    public int getRandomDistance() {
        Random random = new Random();
        return random.nextInt(90) + 10;
    }

    @Override
    public ResponseEntity<Object> getTableData() {
        GlobalVariables distanceTable = GlobalVariables.getGlobalVariable(true);
        try {
            String[] headers = new String[]{"City A", "City B", "City C", "City D", "City E", "City F", "City G", "City H", "City I"};
            String[] rowHeaders = new String[]{"City A", "City B", "City C", "City D", "City E", "City F", "City G", "City H", "City I", "City J"};

            ArrayList<String> usedHeaders = new ArrayList<>();
            for (String rowHeader : headers) {
                for (String header : rowHeaders) {
                    if (!rowHeader.equals(header) && !usedHeaders.contains(header)) {
                        distanceTable.createdTable.add(new DistanceTable(rowHeader, header, getRandomDistance()));
                    }
                }
                usedHeaders.add(rowHeader);
            }
            return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, distanceTable.createdTable, Constant.SUCCESS);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(HttpStatus.MULTI_STATUS, null, Constant.SUCCESS);
        }
    }

    public void setDistanceBetweenCitiesForCorrectAnswers(ArrayList<DistanceTable> tableData, int userId){

        ShortestDistanceBetweenCitiesEntity shortestDistanceBetweenCitiesEntity = new ShortestDistanceBetweenCitiesEntity();

        shortestDistanceBetweenCitiesEntity.setId(userId);

        shortestDistanceBetweenCitiesEntity.setAb(String.valueOf(tableData.get(0).getWeight()));
        shortestDistanceBetweenCitiesEntity.setAc(String.valueOf(tableData.get(1).getWeight()));
        shortestDistanceBetweenCitiesEntity.setAd(String.valueOf(tableData.get(2).getWeight()));
        shortestDistanceBetweenCitiesEntity.setAe(String.valueOf(tableData.get(3).getWeight()));
        shortestDistanceBetweenCitiesEntity.setAf(String.valueOf(tableData.get(4).getWeight()));
        shortestDistanceBetweenCitiesEntity.setAg(String.valueOf(tableData.get(5).getWeight()));
        shortestDistanceBetweenCitiesEntity.setAh(String.valueOf(tableData.get(6).getWeight()));
        shortestDistanceBetweenCitiesEntity.setAi(String.valueOf(tableData.get(7).getWeight()));
        shortestDistanceBetweenCitiesEntity.setAj(String.valueOf(tableData.get(8).getWeight()));


        shortestDistanceBetweenCitiesEntity.setBc(String.valueOf(tableData.get(9).getWeight()));
        shortestDistanceBetweenCitiesEntity.setBd(String.valueOf(tableData.get(10).getWeight()));
        shortestDistanceBetweenCitiesEntity.setBe(String.valueOf(tableData.get(11).getWeight()));
        shortestDistanceBetweenCitiesEntity.setBf(String.valueOf(tableData.get(12).getWeight()));
        shortestDistanceBetweenCitiesEntity.setBg(String.valueOf(tableData.get(13).getWeight()));
        shortestDistanceBetweenCitiesEntity.setBh(String.valueOf(tableData.get(14).getWeight()));
        shortestDistanceBetweenCitiesEntity.setBi(String.valueOf(tableData.get(15).getWeight()));
        shortestDistanceBetweenCitiesEntity.setBj(String.valueOf(tableData.get(16).getWeight()));


        shortestDistanceBetweenCitiesEntity.setCd(String.valueOf(tableData.get(17).getWeight()));
        shortestDistanceBetweenCitiesEntity.setCe(String.valueOf(tableData.get(18).getWeight()));
        shortestDistanceBetweenCitiesEntity.setCf(String.valueOf(tableData.get(19).getWeight()));
        shortestDistanceBetweenCitiesEntity.setCg(String.valueOf(tableData.get(20).getWeight()));
        shortestDistanceBetweenCitiesEntity.setCh(String.valueOf(tableData.get(21).getWeight()));
        shortestDistanceBetweenCitiesEntity.setCi(String.valueOf(tableData.get(22).getWeight()));
        shortestDistanceBetweenCitiesEntity.setCj(String.valueOf(tableData.get(23).getWeight()));


        shortestDistanceBetweenCitiesEntity.setDe(String.valueOf(tableData.get(24).getWeight()));
        shortestDistanceBetweenCitiesEntity.setDf(String.valueOf(tableData.get(25).getWeight()));
        shortestDistanceBetweenCitiesEntity.setDg(String.valueOf(tableData.get(26).getWeight()));
        shortestDistanceBetweenCitiesEntity.setGh(String.valueOf(tableData.get(27).getWeight()));
        shortestDistanceBetweenCitiesEntity.setDi(String.valueOf(tableData.get(28).getWeight()));
        shortestDistanceBetweenCitiesEntity.setDj(String.valueOf(tableData.get(29).getWeight()));


        shortestDistanceBetweenCitiesEntity.setEf(String.valueOf(tableData.get(30).getWeight()));
        shortestDistanceBetweenCitiesEntity.setEg(String.valueOf(tableData.get(31).getWeight()));
        shortestDistanceBetweenCitiesEntity.setEh(String.valueOf(tableData.get(32).getWeight()));
        shortestDistanceBetweenCitiesEntity.setEi(String.valueOf(tableData.get(33).getWeight()));
        shortestDistanceBetweenCitiesEntity.setEj(String.valueOf(tableData.get(34).getWeight()));


        shortestDistanceBetweenCitiesEntity.setFg(String.valueOf(tableData.get(35).getWeight()));
        shortestDistanceBetweenCitiesEntity.setFh(String.valueOf(tableData.get(36).getWeight()));
        shortestDistanceBetweenCitiesEntity.setFi(String.valueOf(tableData.get(37).getWeight()));
        shortestDistanceBetweenCitiesEntity.setFj(String.valueOf(tableData.get(38).getWeight()));

        shortestDistanceBetweenCitiesEntity.setGh(String.valueOf(tableData.get(39).getWeight()));
        shortestDistanceBetweenCitiesEntity.setGi(String.valueOf(tableData.get(40).getWeight()));
        shortestDistanceBetweenCitiesEntity.setGj(String.valueOf(tableData.get(41).getWeight()));


        shortestDistanceBetweenCitiesEntity.setHi(String.valueOf(tableData.get(42).getWeight()));
        shortestDistanceBetweenCitiesEntity.setHj(String.valueOf(tableData.get(43).getWeight()));

        shortestDistanceBetweenCitiesEntity.setIj(String.valueOf(tableData.get(44).getWeight()));

        shortestDistanceBetweenCities.save(shortestDistanceBetweenCitiesEntity);
    }

    public void saveUserDetails(){

    }
}
    

