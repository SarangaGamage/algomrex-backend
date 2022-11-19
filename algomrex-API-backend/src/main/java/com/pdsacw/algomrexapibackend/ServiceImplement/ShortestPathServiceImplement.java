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
    public ResponseEntity<Object> checkUserAnswer(CommonUserAnswer commonUserAnswer) {

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
                List<AnswerResult> checkedResults = new ArrayList<>();
                for (ShortestPathUserAnswer givenShortestPathUserAnswer : commonUserAnswer.getAnswerList()) {
                    if (Objects.equals(pathList.get(index).getPath().replaceAll("City", ""), givenShortestPathUserAnswer.getPath())) {
                        if (Objects.equals(pathList.get(index).getDistance(), givenShortestPathUserAnswer.getDistance())) {
                            checkedResults.add(new AnswerResult(givenShortestPathUserAnswer.getLabelId(), 1, "Both distance and path are correct!!"));
                        } else {
                            checkedResults.add(new AnswerResult(givenShortestPathUserAnswer.getLabelId(), 2, "Only path is correct distance is wrong!!"));
                        }
                    } else {
                        if (Objects.equals(pathList.get(index).getDistance(), givenShortestPathUserAnswer.getDistance())) {
                            checkedResults.add(new AnswerResult(givenShortestPathUserAnswer.getLabelId(), 2, "Only distance is correct and path is wrong!!"));
                        } else {
                            checkedResults.add(new AnswerResult(givenShortestPathUserAnswer.getLabelId(), 0, "Both distance and path are wrong!!"));
                        }
                    }
                    index++;
                }

                return responseHandler.generateResponse(HttpStatus.MULTI_STATUS, checkedResults, Constant.SUCCESS);

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
                return responseHandler.generateResponse(HttpStatus.MULTI_STATUS, checkedResults, Constant.SUCCESS);
            }
        } catch (Exception ex) {
            return responseHandler.generateResponse(HttpStatus.MULTI_STATUS, null, Constant.SUCCESS);
        }
        return responseHandler.generateResponse(HttpStatus.MULTI_STATUS, null, Constant.SUCCESS);
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
            return responseHandler.generateResponse(HttpStatus.MULTI_STATUS, distanceTable.createdTable, Constant.SUCCESS);
        } catch (Exception ex) {
            return responseHandler.generateResponse(HttpStatus.MULTI_STATUS, null, Constant.SUCCESS);
        }
    }
}
    

