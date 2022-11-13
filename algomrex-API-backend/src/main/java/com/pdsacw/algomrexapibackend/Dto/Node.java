package com.pdsacw.algomrexapibackend.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.*;


@Getter
@Setter
@RequiredArgsConstructor
public class Node<T> implements Comparable<Node<T>>  {


    private final T name;
    private Integer distance = Integer.MAX_VALUE;
    private List<Node<T>> shortestPath = new LinkedList<>();
    private Map<Node<T>, Integer> adjacentNodes = new HashMap<>();



    public void addAdjacentNode(Node<T> node, int weight) {
        adjacentNodes.put(node, weight);
    }

    public void addAdjacentNode(Node<T> node, int weight, Node<T> destination) {
        adjacentNodes.put(node, weight);
        node.adjacentNodes.put(destination, weight);
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(this.distance, node.getDistance());
    }
}
