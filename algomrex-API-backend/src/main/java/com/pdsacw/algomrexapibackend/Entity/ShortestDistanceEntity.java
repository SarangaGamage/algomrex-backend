package com.pdsacw.algomrexapibackend.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="shortest_distance")
public class ShortestDistanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "game_id")
    private String gameId;
    @Column(name = "city_a")
    private Integer cityA;
    @Column(name = "city_b")
    private Integer cityB;
    @Column(name = "city_c")
    private Integer cityC;
    @Column(name = "city_d")
    private Integer cityD;
    @Column(name = "city_e")
    private Integer cityE;
    @Column(name = "city_f")
    private Integer cityF;
    @Column(name = "city_g")
    private Integer cityG;
    @Column(name = "city_h")
    private Integer cityH;
    @Column(name = "city_i")
    private Integer cityI;
    @Column(name = "city_j")
    private Integer cityJ;
}