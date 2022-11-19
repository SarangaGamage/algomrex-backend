package com.pdsacw.algomrexapibackend.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.*;


@Getter
@Setter
@RequiredArgsConstructor
public class Node implements Comparable<Node>  {


    private String name;
    private Integer distance = Integer.MAX_VALUE;
    private List<Node> shortestPath = new LinkedList<>();
    public Map<Node, Integer> adjacentNodes = new HashMap<>();

    public void addAdjacentNode(Node node, int weight) {
        adjacentNodes.put(node, weight);
    }

    public void addAdjacentNode(Node node, int weight, Node destination) {
        adjacentNodes.put(node, weight);
        node.adjacentNodes.put(destination, weight);
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(this.distance, node.getDistance());
    }

    public Node(String name) {
        this.name = name;
    }


}
