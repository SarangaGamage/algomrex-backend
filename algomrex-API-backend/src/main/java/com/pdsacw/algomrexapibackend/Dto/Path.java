package com.pdsacw.algomrexapibackend.Dto;

public class Path {

    private  String name;
    private  String distance;
    private  String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

        public Path(String name, String distance, String path) {
        this.name = name;
        this.distance = distance;
        this.path = path;
    }

    public Path() {
    }
}
