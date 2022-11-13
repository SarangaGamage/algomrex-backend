package com.pdsacw.algomrexapibackend.Dto;

import org.w3c.dom.Node;

import java.util.HashSet;
import java.util.Set;

public class graphDto {

    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node node) {
        nodes.add(node);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }
}
