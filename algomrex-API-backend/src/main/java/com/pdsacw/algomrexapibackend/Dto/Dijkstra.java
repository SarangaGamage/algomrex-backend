package com.pdsacw.algomrexapibackend.Dto;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dijkstra {

    public void calculateShortestPath(Node source) {
        // fist set the distance of source node to 0
        source.setDistance(0);
        // here we have defined the hash set of settle nodes and queue of  unsettle nodes
        Set<Node> settledNodes = new HashSet<>();
        // create an immutable queue here not 0 and not more than 1 item here
        Queue<Node> unsettledNodes = new PriorityQueue<>(Collections.singleton(source));
        while (!unsettledNodes.isEmpty()) {
            // set current node by getting the top value (head) of unsettle nodes and remove the head
            Node currentNode = unsettledNodes.poll();
            // getting all connected nodes of current nodes
            currentNode.getAdjacentNodes()
                    .entrySet().stream()  // convert
                    .filter(entry -> !settledNodes.contains(entry.getKey())) // avoid the already settled nodes
                    .forEach(entry -> {
                        evaluateDistanceAndPath(entry.getKey(), entry.getValue(), currentNode);
                        unsettledNodes.add(entry.getKey());
                    });
            settledNodes.add(currentNode);
        }
    }

    private void evaluateDistanceAndPath(Node adjacentNode, Integer edgeWeight, Node sourceNode) {
        // getting new distance
        Integer newDistance = sourceNode.getDistance() + edgeWeight;
        if (newDistance < adjacentNode.getDistance()) {
            adjacentNode.setDistance(newDistance);
            // concat shortest path of source node and source node
            adjacentNode.setShortestPath(Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode)).toList());
        }

    }

    public ArrayList<Path> printPaths(List<Node> nodes) {
        ArrayList<Path> Data = new ArrayList<Path>();
        nodes.forEach(node -> {
            String path = node.getShortestPath().stream()
                    .map(Node::getName).map(Objects::toString)
                    .collect(Collectors.joining("-"));
            System.out.println((path.isBlank()
                    ? "%s : %s".formatted(node.getName(), node.getDistance())
                    : "%s-%s : %s".formatted(path, node.getName(), node.getDistance()))
            );

            if(path.isBlank()){
                Data.add(new Path(node.getName(), node.getDistance().toString(), node.getName()));
            }else {
                Data.add(new Path(node.getName(), node.getDistance().toString(), path + "-"+node.getName()));
            }
        });

        return Data;
    }

}
